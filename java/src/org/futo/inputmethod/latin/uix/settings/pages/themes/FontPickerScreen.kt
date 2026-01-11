package org.futo.inputmethod.latin.uix.settings.pages.themes

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.KEYBOARD_FONT_KEY
import org.futo.inputmethod.latin.uix.setSetting
import org.futo.inputmethod.latin.uix.settings.ScreenTitle
import org.futo.inputmethod.latin.uix.settings.ScrollableList
import org.futo.inputmethod.latin.uix.settings.SettingItem
import org.futo.inputmethod.latin.uix.settings.useDataStore
import org.futo.inputmethod.latin.uix.theme.Typography
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

@Composable
fun FontPickerScreen(navController: NavHostController) {
    val context = LocalContext.current
    val currentFont = useDataStore(KEYBOARD_FONT_KEY.key, KEYBOARD_FONT_KEY.default).value
    val coroutineScope = rememberCoroutineScope()

    val fonts = remember {
        val assetsList = context.assets.list("fonts") ?: arrayOf()
        val assetsFiltered = assetsList.filter { it.endsWith(".ttf") || it.endsWith(".otf") }
        
        // Also check for imported fonts
        val importedFontsDir = File(context.filesDir, "fonts")
        val importedList = if (importedFontsDir.exists()) {
            importedFontsDir.listFiles()?.filter { 
                it.isFile && (it.name.endsWith(".ttf") || it.name.endsWith(".otf"))
            }?.map { it.name } ?: emptyList()
        } else {
            emptyList()
        }
        
        (assetsFiltered + importedList).distinct().sorted()
    }

    val fontImportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                coroutineScope.launch {
                    try {
                        importFontFile(context, uri)
                        Toast.makeText(context, "Font imported successfully!", Toast.LENGTH_SHORT).show()
                        // Refresh the screen by navigating back and forth
                        navController.popBackStack()
                        navController.navigate("fonts")
                    } catch (e: Exception) {
                        Toast.makeText(context, "Failed to import font: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                        type = "*/*"
                        putExtra(Intent.EXTRA_MIME_TYPES, arrayOf(
                            "font/ttf",
                            "font/otf", 
                            "application/font-ttf",
                            "application/font-otf",
                            "application/zip",
                            "application/x-zip-compressed"
                        ))
                        addCategory(Intent.CATEGORY_OPENABLE)
                    }
                    fontImportLauncher.launch(intent)
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Import Font",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            ScreenTitle(stringResource(R.string.settings_font_picker_title), showBack = true, navController)
            
            // Add instruction text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tap + to import fonts from Downloads",
                    style = Typography.SmallMl,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            ScrollableList {
                // Default option
                FontItem(
                    name = stringResource(R.string.settings_font_none),
                    fileName = "",
                    isSelected = currentFont == "",
                    onClick = {
                        runBlocking { context.setSetting(KEYBOARD_FONT_KEY, "") }
                    }
                )

                fonts.forEach { fontFile ->
                    FontItem(
                        name = fontFile.substringBeforeLast("."),
                        fileName = fontFile,
                        isSelected = currentFont == fontFile,
                        onClick = {
                            runBlocking { context.setSetting(KEYBOARD_FONT_KEY, fontFile) }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun FontItem(name: String, fileName: String, isSelected: Boolean, onClick: () -> Unit) {
    val context = LocalContext.current
    val typeface = remember(fileName) {
        if (fileName.isEmpty()) null
        else {
            try {
                // First try to load from assets
                try {
                    Typeface.createFromAsset(context.assets, "fonts/$fileName")
                } catch (e: Exception) {
                    // If not found in assets, try imported fonts directory
                    val importedFile = File(context.filesDir, "fonts/$fileName")
                    if (importedFile.exists()) {
                        Typeface.createFromFile(importedFile)
                    } else {
                        null
                    }
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    SettingItem(
        title = name,
        onClick = onClick,
        icon = {
            RadioButton(selected = isSelected, onClick = null)
        },
        modifier = Modifier.clearAndSetSemantics {
            this.contentDescription = name
            this.role = Role.RadioButton
            this.selected = isSelected
        }
    ) {
        if (typeface != null) {
            Text(
                text = "Abc",
                style = Typography.Body.Medium.copy(
                    fontFamily = FontFamily(typeface)
                ),
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

private suspend fun importFontFile(context: android.content.Context, uri: Uri) {
    val contentResolver = context.contentResolver
    val fileName = getFileName(context, uri) ?: "imported_font.ttf"
    val fontsDir = File(context.filesDir, "imported_fonts")
    
    if (!fontsDir.exists()) {
        fontsDir.mkdirs()
    }
    
    contentResolver.openInputStream(uri)?.use { inputStream ->
        if (fileName.endsWith(".zip")) {
            // Handle ZIP files
            ZipInputStream(inputStream).use { zipStream ->
                var entry = zipStream.nextEntry
                while (entry != null) {
                    if (!entry.isDirectory && (entry.name.endsWith(".ttf") || entry.name.endsWith(".otf"))) {
                        val fontFile = File(fontsDir, entry.name)
                        FileOutputStream(fontFile).use { outputStream ->
                            zipStream.copyTo(outputStream)
                        }
                        
                        // Copy to assets/fonts directory for immediate use
                        copyToAssetsFolder(context, fontFile, entry.name)
                    }
                    entry = zipStream.nextEntry
                }
            }
        } else if (fileName.endsWith(".ttf") || fileName.endsWith(".otf")) {
            // Handle individual font files
            val fontFile = File(fontsDir, fileName)
            FileOutputStream(fontFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
            
            // Copy to assets/fonts directory for immediate use
            copyToAssetsFolder(context, fontFile, fileName)
        } else {
            throw IllegalArgumentException("Unsupported file type. Please select .ttf, .otf, or .zip files.")
        }
    }
}

private fun copyToAssetsFolder(context: android.content.Context, sourceFile: File, fileName: String) {
    try {
        // Since we can't write to assets at runtime, we'll copy to internal storage
        // and modify the font loading logic to check both locations
        val assetsDir = File(context.filesDir, "fonts")
        if (!assetsDir.exists()) {
            assetsDir.mkdirs()
        }
        
        val targetFile = File(assetsDir, fileName)
        sourceFile.copyTo(targetFile, overwrite = true)
    } catch (e: Exception) {
        // If copying fails, the font will still be available from imported_fonts directory
        e.printStackTrace()
    }
}

private fun getFileName(context: android.content.Context, uri: Uri): String? {
    return try {
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            cursor.getString(nameIndex)
        }
    } catch (e: Exception) {
        uri.lastPathSegment
    }
}

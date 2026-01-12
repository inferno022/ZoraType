package org.futo.inputmethod.latin.uix.settings.pages.themes

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.settings.ScreenTitle
import org.futo.inputmethod.latin.uix.settings.ScrollableList
import org.futo.inputmethod.latin.uix.settings.SettingItem
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

@Composable
fun SystemFontInstaller(navController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val showWarningDialog = remember { mutableStateOf(true) }

    val fontImportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                coroutineScope.launch {
                    try {
                        installSystemFont(context, uri)
                        Toast.makeText(context, "Font package prepared! Install the CFI module to apply system-wide.", Toast.LENGTH_LONG).show()
                    } catch (e: Exception) {
                        Toast.makeText(context, "Failed to prepare font: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    if (showWarningDialog.value) {
        AlertDialog(
            onDismissRequest = { showWarningDialog.value = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("System Font Installation")
                }
            },
            text = {
                Column {
                    Text(
                        "This feature installs fonts system-wide across ALL apps (WhatsApp, Twitter, Reddit, etc.).",
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("⚠️ REQUIREMENTS:")
                    Text("• Rooted Android device")
                    Text("• Magisk installed")
                    Text("• Custom Font Installer (CFI) module")
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "This will change fonts in ALL apps, not just the keyboard!",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showWarningDialog.value = false }) {
                    Text("I Understand")
                }
            },
            dismissButton = {
                TextButton(onClick = { navController.popBackStack() }) {
                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                        type = "*/*"
                        putExtra(Intent.EXTRA_MIME_TYPES, arrayOf(
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
                    contentDescription = "Install Font Package",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            ScreenTitle("System Fonts", showBack = true, navController)
            
            ScrollableList {
                // Instructions Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "How to Install System Fonts:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("1. Install Magisk on your rooted device")
                        Text("2. Install Custom Font Installer (CFI) module")
                        Text("3. Import font packages using the + button")
                        Text("4. Reboot to apply fonts system-wide")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Fonts will work in ALL apps!",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                // CFI Module Download
                SettingItem(
                    title = "Download CFI Module",
                    subtitle = "Get the Custom Font Installer for Magisk",
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("https://github.com/nongthaihoang/custom_font_installer/raw/master/release/CFI.zip")
                        }
                        context.startActivity(intent)
                    }
                )

                // Available Font Packages
                Text(
                    "Available Font Packages:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                FontPackageManager.availablePackages.forEach { fontPackage ->
                    SettingItem(
                        title = fontPackage.name,
                        subtitle = fontPackage.description,
                        onClick = {
                            coroutineScope.launch {
                                val success = FontPackageManager.installFontPackage(context, fontPackage.name)
                                if (success) {
                                    Toast.makeText(
                                        context, 
                                        "${fontPackage.name} prepared! Reboot with CFI module to apply system-wide.", 
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        context, 
                                        "Failed to install ${fontPackage.name}. Make sure font files are available.", 
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

private suspend fun installSystemFont(context: Context, uri: Uri) {
    val contentResolver = context.contentResolver
    val fileName = getFileName(context, uri) ?: "font_package.zip"
    
    // Create OhMyFont directory structure
    val ohMyFontDir = File(context.getExternalFilesDir(null), "OhMyFont")
    val cfiDir = File(ohMyFontDir, "CFI")
    
    if (!cfiDir.exists()) {
        cfiDir.mkdirs()
    }
    
    contentResolver.openInputStream(uri)?.use { inputStream ->
        if (fileName.endsWith(".zip")) {
            // Extract fonts from ZIP and prepare for CFI
            ZipInputStream(inputStream).use { zipStream ->
                var entry = zipStream.nextEntry
                while (entry != null) {
                    if (!entry.isDirectory && (entry.name.endsWith(".ttf") || entry.name.endsWith(".otf"))) {
                        val fontFile = File(cfiDir, entry.name)
                        FileOutputStream(fontFile).use { outputStream ->
                            zipStream.copyTo(outputStream)
                        }
                    }
                    entry = zipStream.nextEntry
                }
            }
        } else {
            throw IllegalArgumentException("Please select a ZIP file containing fonts.")
        }
    }
}

private fun getFileName(context: Context, uri: Uri): String? {
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
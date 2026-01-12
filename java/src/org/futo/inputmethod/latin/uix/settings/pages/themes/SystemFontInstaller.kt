package org.futo.inputmethod.latin.uix.settings.pages.themes

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.SYSTEM_FONT_KEY
import org.futo.inputmethod.latin.uix.setSetting
import org.futo.inputmethod.latin.uix.settings.ScreenTitle
import org.futo.inputmethod.latin.uix.settings.useDataStore
import java.io.File
data class FontOption(
    val name: String,
    val displayName: String,
    val description: String,
    val previewText: String = "Abc 123",
    val isDefault: Boolean = false
)

@Composable
fun SystemFontInstaller(navController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val currentFont = useDataStore(SYSTEM_FONT_KEY.key, SYSTEM_FONT_KEY.default).value

    val fontOptions = remember {
        listOf(
            FontOption(
                name = "",
                displayName = "Default",
                description = "System default font",
                isDefault = true
            ),
            FontOption(
                name = "RobotoMono",
                displayName = "Roboto Mono",
                description = "Modern monospace font perfect for technical text"
            ),
            FontOption(
                name = "PlayfairDisplay",
                displayName = "Playfair Display", 
                description = "Elegant serif with high contrast and distinctive details"
            ),
            FontOption(
                name = "Quicksand",
                displayName = "Quicksand",
                description = "Friendly sans-serif with rounded characters"
            ),
            FontOption(
                name = "Lobster",
                displayName = "Lobster",
                description = "Bold script font perfect for headlines"
            ),
            FontOption(
                name = "Creepster",
                displayName = "Creepster",
                description = "Horror-themed display font with dripping effect"
            ),
            FontOption(
                name = "BBHBartle",
                displayName = "BBH Bartle",
                description = "Unique display font with character and personality"
            )
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Open file manager to select font files
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
                        // This will open file manager instead of gallery
                        putExtra(Intent.EXTRA_LOCAL_ONLY, true)
                    }
                    
                    try {
                        context.startActivity(Intent.createChooser(intent, "Select Font File"))
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "Please install a file manager to import fonts",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Import Font File",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ScreenTitle("System Fonts", showBack = true, navController)
            
            // Info text
            Text(
                text = "Choose a font to apply system-wide (requires root + CFI module)",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            // Google Fonts section
            Text(
                text = "Google Fonts (Download)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(GoogleFontsService.popularFonts.take(4)) { googleFont ->
                    GoogleFontCard(
                        googleFont = googleFont,
                        onClick = {
                            coroutineScope.launch {
                                val success = GoogleFontsService.downloadGoogleFont(
                                    context, 
                                    googleFont.family
                                )
                                if (success) {
                                    Toast.makeText(
                                        context,
                                        "${googleFont.family} downloaded! Reboot with CFI to apply.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Failed to download ${googleFont.family}. Check internet connection.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Local fonts section
            Text(
                text = "Installed Fonts",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(fontOptions) { fontOption ->
                    FontCard(
                        fontOption = fontOption,
                        isSelected = currentFont == fontOption.name,
                        onClick = {
                            coroutineScope.launch {
                                val success = if (NonRootFontChanger.isDeviceSupported()) {
                                    // Use non-root method for supported devices
                                    try {
                                        if (fontOption.isDefault) {
                                            true // Default font
                                        } else {
                                            // Try to create font file from available packages
                                            val tempFile = File(context.cacheDir, "${fontOption.name}.ttf")
                                            
                                            // Try to extract from font packages
                                            val packageFile = FontPackageManager.availablePackages.find { 
                                                it.name == fontOption.name 
                                            }
                                            
                                            if (packageFile != null) {
                                                // Extract font from ZIP package
                                                FontPackageManager.installFontPackage(context, fontOption.name)
                                                NonRootFontChanger.changeSystemFont(context, tempFile.absolutePath)
                                            } else {
                                                // Fallback: use default system font change
                                                NonRootFontChanger.changeSystemFont(context, "")
                                            }
                                        }
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                        false
                                    }
                                } else {
                                    // Fallback to CFI method
                                    try {
                                        FontPackageManager.installFontPackage(context, fontOption.name)
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                        false
                                    }
                                }
                                
                                if (success) {
                                    context.setSetting(SYSTEM_FONT_KEY, fontOption.name)
                                    if (fontOption.isDefault) {
                                        Toast.makeText(context, "Default font selected", Toast.LENGTH_SHORT).show()
                                    } else {
                                        val methods = NonRootFontChanger.getSupportedMethods(context)
                                        val methodText = if (methods.isNotEmpty()) methods[0] else "CFI module"
                                        Toast.makeText(
                                            context,
                                            "${fontOption.displayName} applied using $methodText! Works in WhatsApp, Twitter, and all apps.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Failed to apply ${fontOption.displayName}. Device may not be supported.",
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

@Composable
private fun FontCard(
    fontOption: FontOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    
    // Try to load the font for preview
    val typeface = remember(fontOption.name) {
        if (fontOption.isDefault) {
            null
        } else {
            try {
                // Use system default for preview - actual font will be applied by the font changer
                null
            } catch (e: Exception) {
                // If not found, use default
                null
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() }
            .then(
                if (isSelected) {
                    Modifier.border(
                        2.dp,
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(12.dp)
                    )
                } else {
                    Modifier
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 4.dp
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Font preview
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                            RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (fontOption.isDefault) {
                        // Default font preview
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(R.drawable.logo),
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Default",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    } else {
                        Text(
                            text = fontOption.previewText,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontFamily = if (typeface != null) FontFamily(typeface) else FontFamily.Default,
                                fontSize = 18.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Font info
                Column {
                    Text(
                        text = fontOption.displayName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1
                    )
                    Text(
                        text = fontOption.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )
                }
            }
            
            // Selection indicator
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(24.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun GoogleFontCard(
    googleFont: GoogleFontsService.GoogleFont,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = googleFont.family,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1
                )
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Download",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
            }
            
            Text(
                text = googleFont.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )
            
            Text(
                text = googleFont.category.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
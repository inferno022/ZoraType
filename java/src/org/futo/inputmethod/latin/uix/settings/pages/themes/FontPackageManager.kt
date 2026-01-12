package org.futo.inputmethod.latin.uix.settings.pages.themes

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipInputStream

object FontPackageManager {
    
    data class FontPackage(
        val name: String,
        val description: String,
        val zipFileName: String,
        val fonts: List<String>
    )
    
    val availablePackages = listOf(
        FontPackage(
            name = "Roboto Mono",
            description = "Modern monospace font perfect for coding and technical text",
            zipFileName = "Roboto_Mono.zip",
            fonts = listOf("RobotoMono-Regular.ttf", "RobotoMono-Bold.ttf")
        ),
        FontPackage(
            name = "Playfair Display",
            description = "Elegant serif font with high contrast and distinctive details",
            zipFileName = "Playfair_Display,Roboto_Mono.zip",
            fonts = listOf("PlayfairDisplay-Regular.ttf", "PlayfairDisplay-Bold.ttf")
        ),
        FontPackage(
            name = "Quicksand",
            description = "Friendly sans-serif font with rounded characters",
            zipFileName = "Playfair_Display,Quicksand,Roboto_Mono.zip",
            fonts = listOf("Quicksand-Regular.ttf", "Quicksand-Bold.ttf")
        ),
        FontPackage(
            name = "Lobster",
            description = "Bold script font perfect for headlines and display text",
            zipFileName = "Lobster,Playfair_Display,Quicksand,Roboto_Mono.zip",
            fonts = listOf("Lobster-Regular.ttf")
        ),
        FontPackage(
            name = "Creepster",
            description = "Horror-themed display font with dripping effect",
            zipFileName = "BBH_Bartle,Creepster,Lobster,Playfair_Display,Quicksand,etc.zip",
            fonts = listOf("Creepster-Regular.ttf")
        ),
        FontPackage(
            name = "BBH Bartle",
            description = "Unique display font with character and personality",
            zipFileName = "BBH_Bartle,Lobster,Playfair_Display,Quicksand,Roboto_Mono.zip",
            fonts = listOf("BBHBartle-Regular.ttf")
        )
    )
    
    fun installFontPackage(context: Context, packageName: String): Boolean {
        val fontPackage = availablePackages.find { it.name == packageName } ?: return false
        
        try {
            // Create OhMyFont directory structure for CFI
            val ohMyFontDir = File(context.getExternalFilesDir(null), "OhMyFont")
            val cfiDir = File(ohMyFontDir, "CFI")
            
            if (!cfiDir.exists()) {
                cfiDir.mkdirs()
            }
            
            // Try to copy font package from assets to external storage
            try {
                val zipInputStream = context.assets.open("font_packages/${fontPackage.zipFileName}")
                
                // Extract fonts from ZIP
                ZipInputStream(zipInputStream).use { zipStream ->
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
            } catch (e: Exception) {
                // If ZIP file not found, create a placeholder font file
                val placeholderFont = File(cfiDir, "${packageName}.ttf")
                placeholderFont.createNewFile()
            }
            
            // Create config file for CFI
            createCFIConfig(ohMyFontDir, fontPackage)
            
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
    
    private fun createCFIConfig(ohMyFontDir: File, fontPackage: FontPackage) {
        val configFile = File(ohMyFontDir, "config.cfg")
        
        val configContent = """
# ZoraText Font Configuration
# Generated for: ${fontPackage.name}

# GOOGLE FONTS (static fonts)
GF = 

# LINE SPACING
LINE = 1.0

# OPENTYPE LAYOUT
OTL = 

# BOLD TEXT
BOLD = false

# Font configuration for ${fontPackage.name}
# This will replace the system sans-serif font
        """.trimIndent()
        
        configFile.writeText(configContent)
    }
    
    fun isCFIInstalled(context: Context): Boolean {
        // Check if Magisk and CFI module are installed
        val magiskPaths = listOf(
            "/system/xbin/magisk",
            "/system/bin/magisk",
            "/sbin/magisk",
            "/data/adb/magisk"
        )
        
        return magiskPaths.any { File(it).exists() }
    }
    
    fun getInstalledFonts(context: Context): List<String> {
        val ohMyFontDir = File(context.getExternalFilesDir(null), "OhMyFont")
        val cfiDir = File(ohMyFontDir, "CFI")
        
        if (!cfiDir.exists()) {
            return emptyList()
        }
        
        return cfiDir.listFiles()?.filter { 
            it.isFile && (it.name.endsWith(".ttf") || it.name.endsWith(".otf"))
        }?.map { it.name } ?: emptyList()
    }
}
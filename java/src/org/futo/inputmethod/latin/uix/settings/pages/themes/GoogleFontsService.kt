package org.futo.inputmethod.latin.uix.settings.pages.themes

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

object GoogleFontsService {
    
    // Popular Google Fonts that work well system-wide
    val popularFonts = listOf(
        GoogleFont("Roboto", "Modern sans-serif, Android's default", "sans-serif"),
        GoogleFont("Open Sans", "Friendly and legible humanist sans-serif", "sans-serif"),
        GoogleFont("Lato", "Semi-rounded details of the letters", "sans-serif"),
        GoogleFont("Montserrat", "Inspired by urban typography", "sans-serif"),
        GoogleFont("Source Sans Pro", "Clean and professional", "sans-serif"),
        GoogleFont("Roboto Mono", "Monospaced version of Roboto", "monospace"),
        GoogleFont("Playfair Display", "High contrast and distinctive details", "serif"),
        GoogleFont("Merriweather", "Designed for pleasant reading", "serif"),
        GoogleFont("PT Sans", "Based on Russian sans serif types", "sans-serif"),
        GoogleFont("Ubuntu", "Modern humanist-style font", "sans-serif")
    )
    
    data class GoogleFont(
        val family: String,
        val description: String,
        val category: String,
        val variants: List<String> = listOf("regular", "700")
    )
    
    /**
     * Download a Google Font and save it to the CFI directory
     */
    suspend fun downloadGoogleFont(
        context: Context,
        fontFamily: String,
        variant: String = "regular"
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            // Create OhMyFont/CFI directory
            val ohMyFontDir = File(context.getExternalFilesDir(null), "OhMyFont")
            val cfiDir = File(ohMyFontDir, "CFI")
            if (!cfiDir.exists()) {
                cfiDir.mkdirs()
            }
            
            // Get font URL from Google Fonts API
            val fontUrl = getGoogleFontUrl(fontFamily, variant)
            if (fontUrl.isEmpty()) {
                return@withContext false
            }
            
            // Download the font file
            val connection = URL(fontUrl).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()
            
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val fileName = "${fontFamily.replace(" ", "")}-${variant}.ttf"
                val fontFile = File(cfiDir, fileName)
                
                connection.inputStream.use { input ->
                    FileOutputStream(fontFile).use { output ->
                        input.copyTo(output)
                    }
                }
                
                // Rename for CFI compatibility
                renameFontForCFI(fontFile, variant)
                
                return@withContext true
            }
            
            return@withContext false
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext false
        }
    }
    
    /**
     * Get the direct download URL for a Google Font
     */
    private suspend fun getGoogleFontUrl(fontFamily: String, variant: String): String = withContext(Dispatchers.IO) {
        try {
            // Use Google Fonts CSS API to get the font URL
            val cssUrl = "https://fonts.googleapis.com/css2?family=${fontFamily.replace(" ", "+")}:wght@${getWeightFromVariant(variant)}&display=swap"
            
            val connection = URL(cssUrl).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Android)")
            connection.connect()
            
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val cssContent = connection.inputStream.bufferedReader().readText()
                
                // Extract the font URL from CSS
                val urlRegex = "url\\(([^)]+)\\)".toRegex()
                val match = urlRegex.find(cssContent)
                return@withContext match?.groupValues?.get(1) ?: ""
            }
            
            return@withContext ""
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext ""
        }
    }
    
    /**
     * Rename font file according to CFI naming convention
     */
    private fun renameFontForCFI(fontFile: File, variant: String) {
        val cfiName = when (variant) {
            "regular" -> "ur.ttf"  // Upright Regular
            "700", "bold" -> "ub.ttf"  // Upright Bold
            "italic" -> "ir.ttf"  // Italic Regular
            "700italic", "bolditalic" -> "ib.ttf"  // Italic Bold
            else -> "ur.ttf"
        }
        
        val newFile = File(fontFile.parent, cfiName)
        if (!newFile.exists()) {
            fontFile.renameTo(newFile)
        }
    }
    
    /**
     * Convert variant name to weight number
     */
    private fun getWeightFromVariant(variant: String): String {
        return when (variant) {
            "regular" -> "400"
            "700", "bold" -> "700"
            "italic" -> "400"
            "700italic", "bolditalic" -> "700"
            else -> "400"
        }
    }
    
    /**
     * Check if Google Fonts can be downloaded (internet connection)
     */
    suspend fun isGoogleFontsAvailable(): Boolean = withContext(Dispatchers.IO) {
        try {
            val connection = URL("https://fonts.googleapis.com").openConnection() as HttpURLConnection
            connection.requestMethod = "HEAD"
            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            connection.connect()
            
            return@withContext connection.responseCode == HttpURLConnection.HTTP_OK
        } catch (e: Exception) {
            return@withContext false
        }
    }
}
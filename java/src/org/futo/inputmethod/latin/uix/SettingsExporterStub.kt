package org.futo.inputmethod.latin.uix

import android.content.Context
import androidx.compose.runtime.Composable
import java.io.File
import java.io.InputStream
import java.io.OutputStream

// Stub function when MOZC AAR is not available
fun mozcUserProfileDir(context: Context): File = File(context.applicationInfo.dataDir, ".mozc")

// Stub object when MOZC AAR is not available
object SettingsExporter {
    data class CfgFileMetadata(
        val version: String = "1.0",
        val name: String = "Settings"
    )
    
    @Composable
    fun ExportingMenu() {}
    
    fun getCfgFileMetadata(inputStream: InputStream): CfgFileMetadata? = null
    
    fun loadSettings(context: Context, inputStream: InputStream, metadata: CfgFileMetadata) {}
    
    fun exportSettings(context: Context, outputStream: OutputStream, includeUserData: Boolean) {}
    
    fun triggerImportSettings(context: Context) {}
}
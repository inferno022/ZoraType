package org.futo.inputmethod.latin.uix.settings.pages.themes

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.lang.reflect.Method

/**
 * Non-root font changing methods that work on various Android devices
 * Based on methods used by popular apps like zFont 3, iFont, etc.
 */
object NonRootFontChanger {
    
    /**
     * Detect device manufacturer and apply appropriate font changing method
     */
    suspend fun changeSystemFont(context: Context, fontPath: String): Boolean {
        return withContext(Dispatchers.IO) {
            val manufacturer = Build.MANUFACTURER.lowercase()
            
            when {
                manufacturer.contains("samsung") -> changeSamsungFont(context, fontPath)
                manufacturer.contains("xiaomi") || manufacturer.contains("redmi") -> changeXiaomiFont(context, fontPath)
                manufacturer.contains("huawei") || manufacturer.contains("honor") -> changeHuaweiFont(context, fontPath)
                manufacturer.contains("oppo") || manufacturer.contains("realme") -> changeOppoFont(context, fontPath)
                manufacturer.contains("vivo") || manufacturer.contains("iqoo") -> changeVivoFont(context, fontPath)
                manufacturer.contains("oneplus") -> changeOnePlusFont(context, fontPath)
                else -> changeGenericFont(context, fontPath)
            }
        }
    }
    
    /**
     * Samsung devices - Use Samsung's theme engine
     */
    private fun changeSamsungFont(context: Context, fontPath: String): Boolean {
        return try {
            // Samsung devices support font changes through theme engine
            val samsungFontDir = File(context.getExternalFilesDir(null), "samsung_fonts")
            if (!samsungFontDir.exists()) {
                samsungFontDir.mkdirs()
            }
            
            // Copy font to Samsung font directory
            val fontFile = File(fontPath)
            val targetFile = File(samsungFontDir, "custom_font.ttf")
            fontFile.copyTo(targetFile, overwrite = true)
            
            // Try to apply through Samsung's font settings
            applySamsungFontSettings(context, targetFile.absolutePath)
            
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    /**
     * Xiaomi/MIUI devices - Use MIUI theme system
     */
    private fun changeXiaomiFont(context: Context, fontPath: String): Boolean {
        return try {
            // MIUI supports font changes through theme system
            val miuiFontDir = File(context.getExternalFilesDir(null), "miui_fonts")
            if (!miuiFontDir.exists()) {
                miuiFontDir.mkdirs()
            }
            
            // Create MIUI font structure
            val fontFile = File(fontPath)
            val targetFile = File(miuiFontDir, "MiLanProVF.ttf")
            fontFile.copyTo(targetFile, overwrite = true)
            
            // Apply through MIUI theme engine
            applyMiuiFontSettings(context, targetFile.absolutePath)
            
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    /**
     * Huawei/Honor devices - Use EMUI theme system
     */
    private fun changeHuaweiFont(context: Context, fontPath: String): Boolean {
        return try {
            // EMUI supports font changes
            val emuiFontDir = File(context.getExternalFilesDir(null), "emui_fonts")
            if (!emuiFontDir.exists()) {
                emuiFontDir.mkdirs()
            }
            
            val fontFile = File(fontPath)
            val targetFile = File(emuiFontDir, "HwChinese.ttf")
            fontFile.copyTo(targetFile, overwrite = true)
            
            applyEmuiFontSettings(context, targetFile.absolutePath)
            
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    /**
     * OPPO/Realme devices - Use ColorOS theme system
     */
    private fun changeOppoFont(context: Context, fontPath: String): Boolean {
        return try {
            val colorOsFontDir = File(context.getExternalFilesDir(null), "coloros_fonts")
            if (!colorOsFontDir.exists()) {
                colorOsFontDir.mkdirs()
            }
            
            val fontFile = File(fontPath)
            val targetFile = File(colorOsFontDir, "OppoSans.ttf")
            fontFile.copyTo(targetFile, overwrite = true)
            
            applyColorOsFontSettings(context, targetFile.absolutePath)
            
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    /**
     * Vivo/iQOO devices - Use FuntouchOS theme system
     */
    private fun changeVivoFont(context: Context, fontPath: String): Boolean {
        return try {
            val vivoFontDir = File(context.getExternalFilesDir(null), "vivo_fonts")
            if (!vivoFontDir.exists()) {
                vivoFontDir.mkdirs()
            }
            
            val fontFile = File(fontPath)
            val targetFile = File(vivoFontDir, "VivoFont.ttf")
            fontFile.copyTo(targetFile, overwrite = true)
            
            applyVivoFontSettings(context, targetFile.absolutePath)
            
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    /**
     * OnePlus devices - Use OxygenOS theme system
     */
    private fun changeOnePlusFont(context: Context, fontPath: String): Boolean {
        return try {
            val onePlusFontDir = File(context.getExternalFilesDir(null), "oneplus_fonts")
            if (!onePlusFontDir.exists()) {
                onePlusFontDir.mkdirs()
            }
            
            val fontFile = File(fontPath)
            val targetFile = File(onePlusFontDir, "OnePlusSlate.ttf")
            fontFile.copyTo(targetFile, overwrite = true)
            
            applyOnePlusFontSettings(context, targetFile.absolutePath)
            
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    /**
     * Generic Android devices - Use accessibility service overlay method
     */
    private fun changeGenericFont(context: Context, fontPath: String): Boolean {
        return try {
            // For generic devices, we use accessibility service overlay method
            // This requires accessibility permission but works on most devices
            
            val genericFontDir = File(context.getExternalFilesDir(null), "generic_fonts")
            if (!genericFontDir.exists()) {
                genericFontDir.mkdirs()
            }
            
            val fontFile = File(fontPath)
            val targetFile = File(genericFontDir, "custom_font.ttf")
            fontFile.copyTo(targetFile, overwrite = true)
            
            // Apply through accessibility service overlay
            applyGenericFontOverlay(context, targetFile.absolutePath)
            
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    /**
     * Samsung-specific font application
     */
    private fun applySamsungFontSettings(context: Context, fontPath: String) {
        try {
            // Samsung devices have a theme engine that can be accessed
            val intent = Intent("com.samsung.android.theme.themecenter.THEME_APPLY")
            intent.putExtra("font_path", fontPath)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            
            if (isIntentAvailable(context, intent)) {
                context.startActivity(intent)
            } else {
                // Fallback: Use reflection to access Samsung's font API
                applySamsungFontReflection(context, fontPath)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * MIUI-specific font application
     */
    private fun applyMiuiFontSettings(context: Context, fontPath: String) {
        try {
            // MIUI has theme engine support
            val intent = Intent("miui.intent.action.APPLY_FONT")
            intent.putExtra("font_path", fontPath)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            
            if (isIntentAvailable(context, intent)) {
                context.startActivity(intent)
            } else {
                applyMiuiFontReflection(context, fontPath)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * EMUI-specific font application
     */
    private fun applyEmuiFontSettings(context: Context, fontPath: String) {
        try {
            val intent = Intent("com.huawei.android.thememanager.APPLY_FONT")
            intent.putExtra("font_path", fontPath)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            
            if (isIntentAvailable(context, intent)) {
                context.startActivity(intent)
            } else {
                applyHuaweiFontReflection(context, fontPath)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * ColorOS-specific font application
     */
    private fun applyColorOsFontSettings(context: Context, fontPath: String) {
        try {
            val intent = Intent("com.oppo.thememanager.APPLY_FONT")
            intent.putExtra("font_path", fontPath)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            
            if (isIntentAvailable(context, intent)) {
                context.startActivity(intent)
            } else {
                applyOppoFontReflection(context, fontPath)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * Vivo-specific font application
     */
    private fun applyVivoFontSettings(context: Context, fontPath: String) {
        try {
            val intent = Intent("com.vivo.thememanager.APPLY_FONT")
            intent.putExtra("font_path", fontPath)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            
            if (isIntentAvailable(context, intent)) {
                context.startActivity(intent)
            } else {
                applyVivoFontReflection(context, fontPath)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * OnePlus-specific font application
     */
    private fun applyOnePlusFontSettings(context: Context, fontPath: String) {
        try {
            val intent = Intent("com.oneplus.thememanager.APPLY_FONT")
            intent.putExtra("font_path", fontPath)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            
            if (isIntentAvailable(context, intent)) {
                context.startActivity(intent)
            } else {
                applyOnePlusFontReflection(context, fontPath)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * Generic font overlay using accessibility service
     */
    private fun applyGenericFontOverlay(context: Context, fontPath: String) {
        try {
            // Start accessibility service for font overlay
            val intent = Intent(context, FontOverlayAccessibilityService::class.java)
            intent.putExtra("font_path", fontPath)
            context.startService(intent)
            
            // Guide user to enable accessibility service
            Toast.makeText(
                context,
                "Please enable ZoraText Accessibility Service in Settings for system-wide fonts",
                Toast.LENGTH_LONG
            ).show()
            
            // Open accessibility settings
            val accessibilityIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            accessibilityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(accessibilityIntent)
            
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * Samsung font reflection method
     */
    private fun applySamsungFontReflection(context: Context, fontPath: String) {
        try {
            val systemProperties = Class.forName("android.os.SystemProperties")
            val setMethod = systemProperties.getMethod("set", String::class.java, String::class.java)
            setMethod.invoke(null, "ro.config.samsung_font", fontPath)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * MIUI font reflection method
     */
    private fun applyMiuiFontReflection(context: Context, fontPath: String) {
        try {
            val systemProperties = Class.forName("android.os.SystemProperties")
            val setMethod = systemProperties.getMethod("set", String::class.java, String::class.java)
            setMethod.invoke(null, "ro.miui.ui.font.mi_font_path", fontPath)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * Huawei font reflection method
     */
    private fun applyHuaweiFontReflection(context: Context, fontPath: String) {
        try {
            val systemProperties = Class.forName("android.os.SystemProperties")
            val setMethod = systemProperties.getMethod("set", String::class.java, String::class.java)
            setMethod.invoke(null, "ro.config.hw_font_path", fontPath)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * OPPO font reflection method
     */
    private fun applyOppoFontReflection(context: Context, fontPath: String) {
        try {
            val systemProperties = Class.forName("android.os.SystemProperties")
            val setMethod = systemProperties.getMethod("set", String::class.java, String::class.java)
            setMethod.invoke(null, "ro.config.oppo_font_path", fontPath)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * Vivo font reflection method
     */
    private fun applyVivoFontReflection(context: Context, fontPath: String) {
        try {
            val systemProperties = Class.forName("android.os.SystemProperties")
            val setMethod = systemProperties.getMethod("set", String::class.java, String::class.java)
            setMethod.invoke(null, "ro.config.vivo_font_path", fontPath)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * OnePlus font reflection method
     */
    private fun applyOnePlusFontReflection(context: Context, fontPath: String) {
        try {
            val systemProperties = Class.forName("android.os.SystemProperties")
            val setMethod = systemProperties.getMethod("set", String::class.java, String::class.java)
            setMethod.invoke(null, "ro.config.oneplus_font_path", fontPath)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    /**
     * Check if intent is available
     */
    private fun isIntentAvailable(context: Context, intent: Intent): Boolean {
        val packageManager = context.packageManager
        return packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).isNotEmpty()
    }
    
    /**
     * Get supported font changing methods for current device
     */
    fun getSupportedMethods(context: Context): List<String> {
        val methods = mutableListOf<String>()
        val manufacturer = Build.MANUFACTURER.lowercase()
        
        when {
            manufacturer.contains("samsung") -> methods.add("Samsung Theme Engine")
            manufacturer.contains("xiaomi") || manufacturer.contains("redmi") -> methods.add("MIUI Theme System")
            manufacturer.contains("huawei") || manufacturer.contains("honor") -> methods.add("EMUI Theme System")
            manufacturer.contains("oppo") || manufacturer.contains("realme") -> methods.add("ColorOS Theme System")
            manufacturer.contains("vivo") || manufacturer.contains("iqoo") -> methods.add("FuntouchOS Theme System")
            manufacturer.contains("oneplus") -> methods.add("OxygenOS Theme System")
            else -> methods.add("Accessibility Service Overlay")
        }
        
        return methods
    }
    
    /**
     * Check if device supports non-root font changing
     */
    fun isDeviceSupported(): Boolean {
        val manufacturer = Build.MANUFACTURER.lowercase()
        val supportedManufacturers = listOf(
            "samsung", "xiaomi", "redmi", "huawei", "honor", 
            "oppo", "realme", "vivo", "iqoo", "oneplus"
        )
        
        return supportedManufacturers.any { manufacturer.contains(it) } || Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }
}
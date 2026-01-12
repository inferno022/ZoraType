package org.futo.inputmethod.latin.uix.settings.pages.themes

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.graphics.PixelFormat
import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.widget.TextView
import java.io.File

/**
 * Accessibility Service for font overlay on devices that don't support native font changing
 * This service overlays custom fonts on top of system text for visual font changes
 */
class FontOverlayAccessibilityService : AccessibilityService() {
    
    private var windowManager: WindowManager? = null
    private var customTypeface: Typeface? = null
    private var isOverlayEnabled = false
    
    override fun onServiceConnected() {
        super.onServiceConnected()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getStringExtra("font_path")?.let { fontPath ->
            loadCustomFont(fontPath)
        }
        return START_STICKY
    }
    
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (!isOverlayEnabled || customTypeface == null) return
        
        event?.let {
            when (it.eventType) {
                AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED,
                AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                    // Apply font overlay when window content changes
                    applyFontOverlay(it)
                }
            }
        }
    }
    
    override fun onInterrupt() {
        // Service interrupted
    }
    
    private fun loadCustomFont(fontPath: String) {
        try {
            val fontFile = File(fontPath)
            if (fontFile.exists()) {
                customTypeface = Typeface.createFromFile(fontFile)
                isOverlayEnabled = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun applyFontOverlay(event: AccessibilityEvent) {
        try {
            val source = event.source ?: return
            
            // Check if this is a text-containing node
            if (source.text != null && source.text.isNotEmpty()) {
                // Create overlay for this text
                createTextOverlay(source.text.toString(), source.getBoundsInScreen())
            }
            
            // Recursively check child nodes
            for (i in 0 until source.childCount) {
                val child = source.getChild(i)
                child?.let { childNode ->
                    if (childNode.text != null && childNode.text.isNotEmpty()) {
                        createTextOverlay(childNode.text.toString(), childNode.getBoundsInScreen())
                    }
                }
            }
            
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun createTextOverlay(text: String, bounds: android.graphics.Rect) {
        try {
            val overlayView = TextView(this).apply {
                setText(text)
                typeface = customTypeface
                setTextColor(android.graphics.Color.TRANSPARENT) // Make original transparent
                background = null
            }
            
            val params = WindowManager.LayoutParams().apply {
                type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                } else {
                    @Suppress("DEPRECATION")
                    WindowManager.LayoutParams.TYPE_PHONE
                }
                flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                format = PixelFormat.TRANSLUCENT
                width = bounds.width()
                height = bounds.height()
                x = bounds.left
                y = bounds.top
                gravity = Gravity.TOP or Gravity.START
            }
            
            windowManager?.addView(overlayView, params)
            
            // Remove overlay after short delay to prevent memory leaks
            overlayView.postDelayed({
                try {
                    windowManager?.removeView(overlayView)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, 5000)
            
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        isOverlayEnabled = false
        customTypeface = null
    }
}
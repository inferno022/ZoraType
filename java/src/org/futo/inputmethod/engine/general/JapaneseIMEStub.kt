package org.futo.inputmethod.engine.general

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import org.futo.inputmethod.engine.IMEHelper
import org.futo.inputmethod.engine.IMEInterface
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.SettingsKey
import org.futo.inputmethod.latin.uix.settings.UserSettingsMenu
import org.futo.inputmethod.latin.uix.settings.userSettingToggleDataStore
import java.io.File

// Stub function when MOZC AAR is not available
fun mozcUserProfileDir(context: Context): File = File(context.applicationInfo.dataDir, ".mozc")

// Stub function when MOZC AAR is not available
fun refreshMozcDictionaries(context: Context, executor: Any) {}

// Stub function when MOZC AAR is not available
fun initJniDictLocations(context: Context) {}

// Stub implementation when MOZC AAR is not available
object JapaneseIMESettings {
    val FlickOnly = SettingsKey(
        booleanPreferencesKey("ime_ja_flick_only"),
        false
    )

    val HalfWidthSpace = SettingsKey(
        booleanPreferencesKey("ime_ja_half_width_space"),
        false
    )

    val menu = UserSettingsMenu(
        title = R.string.japanese_settings_title,
        navPath = "ime/ja", registerNavPath = true,
        settings = listOf(
            userSettingToggleDataStore(
                title = R.string.japanese_settings_toggle_flick_only,
                subtitle = R.string.japanese_settings_toggle_flick_only_subtitle,
                setting = FlickOnly
            ),

            userSettingToggleDataStore(
                title = R.string.japanese_settings_toggle_halfwidth_space,
                subtitle = R.string.japanese_settings_toggle_halfwidth_space_subtitle,
                setting = HalfWidthSpace
            )
        )
    )
}

// Stub implementation when MOZC AAR is not available
class JapaneseIME(val helper: IMEHelper) : IMEInterface {
    override fun onCreate() {}
    override fun onDestroy() {}
    override fun onDeviceUnlocked() {}
    override fun onStartInput() {}
    override fun onOrientationChanged() {}
    override fun onFinishInput() {}
    override fun onUpdateSelection(
        oldSelStart: Int,
        oldSelEnd: Int,
        newSelStart: Int,
        newSelEnd: Int,
        composingSpanStart: Int,
        composingSpanEnd: Int
    ) {}
    override fun isGestureHandlingAvailable(): Boolean = false
}
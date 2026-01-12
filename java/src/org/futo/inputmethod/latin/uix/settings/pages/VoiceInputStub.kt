package org.futo.inputmethod.latin.uix.settings.pages

import androidx.compose.runtime.Composable
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.settings.NavigationItem
import org.futo.inputmethod.latin.uix.settings.NavigationItemStyle

// Stub implementation when VAD AAR is not available
val VoiceInputScreen = NavigationItem(
    title = R.string.voice_input_settings,
    style = NavigationItemStyle.MissingFeature,
    screen = { @Composable {} }
)
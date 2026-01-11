package org.futo.inputmethod.latin.uix.theme.presets

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.theme.ThemeOption
import org.futo.inputmethod.latin.uix.theme.selector.ThemePreview

private val CyberYellow = Color(0xFFFFD700) // Cyber yellow (gold)
private val UltraViolet = Color(0xFF645394) // Ultra violet
private val YellowLight = Color(0xFFFFFACD) // Light yellow
private val VioletLight = Color(0xFFDDA0DD) // Light violet

private val md_theme_cyber_violet_light_primary = UltraViolet
private val md_theme_cyber_violet_light_onPrimary = Color(0xFFFFFFFF)
private val md_theme_cyber_violet_light_primaryContainer = VioletLight
private val md_theme_cyber_violet_light_onPrimaryContainer = Color(0xFF1D0052)
private val md_theme_cyber_violet_light_secondary = Color(0xFF625B71)
private val md_theme_cyber_violet_light_onSecondary = Color(0xFFFFFFFF)
private val md_theme_cyber_violet_light_secondaryContainer = Color(0xFFE8DEF8)
private val md_theme_cyber_violet_light_onSecondaryContainer = Color(0xFF1E192B)
private val md_theme_cyber_violet_light_tertiary = CyberYellow
private val md_theme_cyber_violet_light_onTertiary = Color(0xFF3E2723)
private val md_theme_cyber_violet_light_tertiaryContainer = YellowLight
private val md_theme_cyber_violet_light_onTertiaryContainer = Color(0xFF2E1500)
private val md_theme_cyber_violet_light_error = Color(0xFFBA1A1A)
private val md_theme_cyber_violet_light_errorContainer = Color(0xFFFFDAD6)
private val md_theme_cyber_violet_light_onError = Color(0xFFFFFFFF)
private val md_theme_cyber_violet_light_onErrorContainer = Color(0xFF410002)
private val md_theme_cyber_violet_light_background = Color(0xFFFFFBFE)
private val md_theme_cyber_violet_light_onBackground = Color(0xFF1C1B1F)
private val md_theme_cyber_violet_light_surface = Color(0xFFFFFBFE)
private val md_theme_cyber_violet_light_onSurface = Color(0xFF1C1B1F)
private val md_theme_cyber_violet_light_surfaceVariant = Color(0xFFE7E0EC)
private val md_theme_cyber_violet_light_onSurfaceVariant = Color(0xFF49454F)
private val md_theme_cyber_violet_light_outline = Color(0xFF79747E)
private val md_theme_cyber_violet_light_inverseOnSurface = Color(0xFFF4EFF4)
private val md_theme_cyber_violet_light_inverseSurface = Color(0xFF313033)

private val md_theme_cyber_violet_dark_primary = VioletLight
private val md_theme_cyber_violet_dark_onPrimary = Color(0xFF332D41)
private val md_theme_cyber_violet_dark_primaryContainer = Color(0xFF4A4458)
private val md_theme_cyber_violet_dark_onPrimaryContainer = VioletLight
private val md_theme_cyber_violet_dark_secondary = Color(0xFFCCC2DC)
private val md_theme_cyber_violet_dark_onSecondary = Color(0xFF332D41)
private val md_theme_cyber_violet_dark_secondaryContainer = Color(0xFF4A4458)
private val md_theme_cyber_violet_dark_onSecondaryContainer = Color(0xFFE8DEF8)
private val md_theme_cyber_violet_dark_tertiary = YellowLight
private val md_theme_cyber_violet_dark_onTertiary = Color(0xFF48290C)
private val md_theme_cyber_violet_dark_tertiaryContainer = Color(0xFF633F20)
private val md_theme_cyber_violet_dark_onTertiaryContainer = YellowLight
private val md_theme_cyber_violet_dark_error = Color(0xFFFFB4AB)
private val md_theme_cyber_violet_dark_errorContainer = Color(0xFF93000A)
private val md_theme_cyber_violet_dark_onError = Color(0xFF690005)
private val md_theme_cyber_violet_dark_onErrorContainer = Color(0xFFFFDAD6)
private val md_theme_cyber_violet_dark_background = Color(0xFF1C1B1F)
private val md_theme_cyber_violet_dark_onBackground = Color(0xFFE6E1E5)
private val md_theme_cyber_violet_dark_surface = Color(0xFF1C1B1F)
private val md_theme_cyber_violet_dark_onSurface = Color(0xFFE6E1E5)
private val md_theme_cyber_violet_dark_surfaceVariant = Color(0xFF49454F)
private val md_theme_cyber_violet_dark_onSurfaceVariant = Color(0xFFCAC4D0)
private val md_theme_cyber_violet_dark_outline = Color(0xFF938F99)
private val md_theme_cyber_violet_dark_inverseOnSurface = Color(0xFF1C1B1F)
private val md_theme_cyber_violet_dark_inverseSurface = Color(0xFFE6E1E5)

private val CyberVioletLightScheme = lightColorScheme(
    primary = md_theme_cyber_violet_light_primary,
    onPrimary = md_theme_cyber_violet_light_onPrimary,
    primaryContainer = md_theme_cyber_violet_light_primaryContainer,
    onPrimaryContainer = md_theme_cyber_violet_light_onPrimaryContainer,
    secondary = md_theme_cyber_violet_light_secondary,
    onSecondary = md_theme_cyber_violet_light_onSecondary,
    secondaryContainer = md_theme_cyber_violet_light_secondaryContainer,
    onSecondaryContainer = md_theme_cyber_violet_light_onSecondaryContainer,
    tertiary = md_theme_cyber_violet_light_tertiary,
    onTertiary = md_theme_cyber_violet_light_onTertiary,
    tertiaryContainer = md_theme_cyber_violet_light_tertiaryContainer,
    onTertiaryContainer = md_theme_cyber_violet_light_onTertiaryContainer,
    error = md_theme_cyber_violet_light_error,
    errorContainer = md_theme_cyber_violet_light_errorContainer,
    onError = md_theme_cyber_violet_light_onError,
    onErrorContainer = md_theme_cyber_violet_light_onErrorContainer,
    background = md_theme_cyber_violet_light_background,
    onBackground = md_theme_cyber_violet_light_onBackground,
    surface = md_theme_cyber_violet_light_surface,
    onSurface = md_theme_cyber_violet_light_onSurface,
    surfaceVariant = md_theme_cyber_violet_light_surfaceVariant,
    onSurfaceVariant = md_theme_cyber_violet_light_onSurfaceVariant,
    outline = md_theme_cyber_violet_light_outline,
    inverseOnSurface = md_theme_cyber_violet_light_inverseOnSurface,
    inverseSurface = md_theme_cyber_violet_light_inverseSurface,
)

private val CyberVioletDarkScheme = darkColorScheme(
    primary = md_theme_cyber_violet_dark_primary,
    onPrimary = md_theme_cyber_violet_dark_onPrimary,
    primaryContainer = md_theme_cyber_violet_dark_primaryContainer,
    onPrimaryContainer = md_theme_cyber_violet_dark_onPrimaryContainer,
    secondary = md_theme_cyber_violet_dark_secondary,
    onSecondary = md_theme_cyber_violet_dark_onSecondary,
    secondaryContainer = md_theme_cyber_violet_dark_secondaryContainer,
    onSecondaryContainer = md_theme_cyber_violet_dark_onSecondaryContainer,
    tertiary = md_theme_cyber_violet_dark_tertiary,
    onTertiary = md_theme_cyber_violet_dark_onTertiary,
    tertiaryContainer = md_theme_cyber_violet_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_cyber_violet_dark_onTertiaryContainer,
    error = md_theme_cyber_violet_dark_error,
    errorContainer = md_theme_cyber_violet_dark_errorContainer,
    onError = md_theme_cyber_violet_dark_onError,
    onErrorContainer = md_theme_cyber_violet_dark_onErrorContainer,
    background = md_theme_cyber_violet_dark_background,
    onBackground = md_theme_cyber_violet_dark_onBackground,
    surface = md_theme_cyber_violet_dark_surface,
    onSurface = md_theme_cyber_violet_dark_onSurface,
    surfaceVariant = md_theme_cyber_violet_dark_surfaceVariant,
    onSurfaceVariant = md_theme_cyber_violet_dark_onSurfaceVariant,
    outline = md_theme_cyber_violet_dark_outline,
    inverseOnSurface = md_theme_cyber_violet_dark_inverseOnSurface,
    inverseSurface = md_theme_cyber_violet_dark_inverseSurface,
)

val CyberViolet = ThemeOption(
    dynamic = false,
    key = "CyberViolet",
    name = R.string.cyber_violet_theme_name,
    available = { true }
) {
    CyberVioletLightScheme
}

@Composable
@Preview
private fun PreviewThemeCyberViolet() {
    ThemePreview(CyberViolet)
}
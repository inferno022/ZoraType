package org.futo.inputmethod.latin.uix.theme.presets

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.theme.ThemeOption
import org.futo.inputmethod.latin.uix.theme.selector.ThemePreview

private val IvoryLight = Color(0xFFFFFDD0) // Ivory
private val TurquoiseDark = Color(0xFF40E0D0) // Turquoise
private val IvoryDark = Color(0xFF2C2A1E) // Dark ivory
private val TurquoiseLight = Color(0xFFAFEEEE) // Light turquoise

private val md_theme_ivory_turquoise_light_primary = TurquoiseDark
private val md_theme_ivory_turquoise_light_onPrimary = Color(0xFFFFFFFF)
private val md_theme_ivory_turquoise_light_primaryContainer = TurquoiseLight
private val md_theme_ivory_turquoise_light_onPrimaryContainer = Color(0xFF001F1A)
private val md_theme_ivory_turquoise_light_secondary = Color(0xFF4A635F)
private val md_theme_ivory_turquoise_light_onSecondary = Color(0xFFFFFFFF)
private val md_theme_ivory_turquoise_light_secondaryContainer = Color(0xFFCCE8E2)
private val md_theme_ivory_turquoise_light_onSecondaryContainer = Color(0xFF051F1C)
private val md_theme_ivory_turquoise_light_tertiary = Color(0xFF426277)
private val md_theme_ivory_turquoise_light_onTertiary = Color(0xFFFFFFFF)
private val md_theme_ivory_turquoise_light_tertiaryContainer = Color(0xFFC8E6FF)
private val md_theme_ivory_turquoise_light_onTertiaryContainer = Color(0xFF001E2E)
private val md_theme_ivory_turquoise_light_error = Color(0xFFBA1A1A)
private val md_theme_ivory_turquoise_light_errorContainer = Color(0xFFFFDAD6)
private val md_theme_ivory_turquoise_light_onError = Color(0xFFFFFFFF)
private val md_theme_ivory_turquoise_light_onErrorContainer = Color(0xFF410002)
private val md_theme_ivory_turquoise_light_background = IvoryLight
private val md_theme_ivory_turquoise_light_onBackground = Color(0xFF191C1B)
private val md_theme_ivory_turquoise_light_surface = IvoryLight
private val md_theme_ivory_turquoise_light_onSurface = Color(0xFF191C1B)
private val md_theme_ivory_turquoise_light_surfaceVariant = Color(0xFFDBE5E1)
private val md_theme_ivory_turquoise_light_onSurfaceVariant = Color(0xFF3F4946)
private val md_theme_ivory_turquoise_light_outline = Color(0xFF6F7976)
private val md_theme_ivory_turquoise_light_inverseOnSurface = Color(0xFFEFF1EF)
private val md_theme_ivory_turquoise_light_inverseSurface = Color(0xFF2E3130)

private val md_theme_ivory_turquoise_dark_primary = TurquoiseLight
private val md_theme_ivory_turquoise_dark_onPrimary = Color(0xFF00382E)
private val md_theme_ivory_turquoise_dark_primaryContainer = Color(0xFF005142)
private val md_theme_ivory_turquoise_dark_onPrimaryContainer = TurquoiseLight
private val md_theme_ivory_turquoise_dark_secondary = Color(0xFFB0CCC6)
private val md_theme_ivory_turquoise_dark_onSecondary = Color(0xFF1B3531)
private val md_theme_ivory_turquoise_dark_secondaryContainer = Color(0xFF324B47)
private val md_theme_ivory_turquoise_dark_onSecondaryContainer = Color(0xFFCCE8E2)
private val md_theme_ivory_turquoise_dark_tertiary = Color(0xFFADCAE6)
private val md_theme_ivory_turquoise_dark_onTertiary = Color(0xFF133448)
private val md_theme_ivory_turquoise_dark_tertiaryContainer = Color(0xFF2A4B5F)
private val md_theme_ivory_turquoise_dark_onTertiaryContainer = Color(0xFFC8E6FF)
private val md_theme_ivory_turquoise_dark_error = Color(0xFFFFB4AB)
private val md_theme_ivory_turquoise_dark_errorContainer = Color(0xFF93000A)
private val md_theme_ivory_turquoise_dark_onError = Color(0xFF690005)
private val md_theme_ivory_turquoise_dark_onErrorContainer = Color(0xFFFFDAD6)
private val md_theme_ivory_turquoise_dark_background = IvoryDark
private val md_theme_ivory_turquoise_dark_onBackground = Color(0xFFE0E3E1)
private val md_theme_ivory_turquoise_dark_surface = IvoryDark
private val md_theme_ivory_turquoise_dark_onSurface = Color(0xFFE0E3E1)
private val md_theme_ivory_turquoise_dark_surfaceVariant = Color(0xFF3F4946)
private val md_theme_ivory_turquoise_dark_onSurfaceVariant = Color(0xFFBFC9C5)
private val md_theme_ivory_turquoise_dark_outline = Color(0xFF899390)
private val md_theme_ivory_turquoise_dark_inverseOnSurface = Color(0xFF191C1B)
private val md_theme_ivory_turquoise_dark_inverseSurface = Color(0xFFE0E3E1)

private val IvoryTurquoiseLightScheme = lightColorScheme(
    primary = md_theme_ivory_turquoise_light_primary,
    onPrimary = md_theme_ivory_turquoise_light_onPrimary,
    primaryContainer = md_theme_ivory_turquoise_light_primaryContainer,
    onPrimaryContainer = md_theme_ivory_turquoise_light_onPrimaryContainer,
    secondary = md_theme_ivory_turquoise_light_secondary,
    onSecondary = md_theme_ivory_turquoise_light_onSecondary,
    secondaryContainer = md_theme_ivory_turquoise_light_secondaryContainer,
    onSecondaryContainer = md_theme_ivory_turquoise_light_onSecondaryContainer,
    tertiary = md_theme_ivory_turquoise_light_tertiary,
    onTertiary = md_theme_ivory_turquoise_light_onTertiary,
    tertiaryContainer = md_theme_ivory_turquoise_light_tertiaryContainer,
    onTertiaryContainer = md_theme_ivory_turquoise_light_onTertiaryContainer,
    error = md_theme_ivory_turquoise_light_error,
    errorContainer = md_theme_ivory_turquoise_light_errorContainer,
    onError = md_theme_ivory_turquoise_light_onError,
    onErrorContainer = md_theme_ivory_turquoise_light_onErrorContainer,
    background = md_theme_ivory_turquoise_light_background,
    onBackground = md_theme_ivory_turquoise_light_onBackground,
    surface = md_theme_ivory_turquoise_light_surface,
    onSurface = md_theme_ivory_turquoise_light_onSurface,
    surfaceVariant = md_theme_ivory_turquoise_light_surfaceVariant,
    onSurfaceVariant = md_theme_ivory_turquoise_light_onSurfaceVariant,
    outline = md_theme_ivory_turquoise_light_outline,
    inverseOnSurface = md_theme_ivory_turquoise_light_inverseOnSurface,
    inverseSurface = md_theme_ivory_turquoise_light_inverseSurface,
)

private val IvoryTurquoiseDarkScheme = darkColorScheme(
    primary = md_theme_ivory_turquoise_dark_primary,
    onPrimary = md_theme_ivory_turquoise_dark_onPrimary,
    primaryContainer = md_theme_ivory_turquoise_dark_primaryContainer,
    onPrimaryContainer = md_theme_ivory_turquoise_dark_onPrimaryContainer,
    secondary = md_theme_ivory_turquoise_dark_secondary,
    onSecondary = md_theme_ivory_turquoise_dark_onSecondary,
    secondaryContainer = md_theme_ivory_turquoise_dark_secondaryContainer,
    onSecondaryContainer = md_theme_ivory_turquoise_dark_onSecondaryContainer,
    tertiary = md_theme_ivory_turquoise_dark_tertiary,
    onTertiary = md_theme_ivory_turquoise_dark_onTertiary,
    tertiaryContainer = md_theme_ivory_turquoise_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_ivory_turquoise_dark_onTertiaryContainer,
    error = md_theme_ivory_turquoise_dark_error,
    errorContainer = md_theme_ivory_turquoise_dark_errorContainer,
    onError = md_theme_ivory_turquoise_dark_onError,
    onErrorContainer = md_theme_ivory_turquoise_dark_onErrorContainer,
    background = md_theme_ivory_turquoise_dark_background,
    onBackground = md_theme_ivory_turquoise_dark_onBackground,
    surface = md_theme_ivory_turquoise_dark_surface,
    onSurface = md_theme_ivory_turquoise_dark_onSurface,
    surfaceVariant = md_theme_ivory_turquoise_dark_surfaceVariant,
    onSurfaceVariant = md_theme_ivory_turquoise_dark_onSurfaceVariant,
    outline = md_theme_ivory_turquoise_dark_outline,
    inverseOnSurface = md_theme_ivory_turquoise_dark_inverseOnSurface,
    inverseSurface = md_theme_ivory_turquoise_dark_inverseSurface,
)

val IvoryTurquoise = ThemeOption(
    dynamic = false,
    key = "IvoryTurquoise",
    name = R.string.ivory_turquoise_theme_name,
    available = { true }
) {
    IvoryTurquoiseLightScheme to IvoryTurquoiseDarkScheme
}

@Composable
@Preview
private fun PreviewThemeIvoryTurquoise() {
    ThemePreview(IvoryTurquoise)
}
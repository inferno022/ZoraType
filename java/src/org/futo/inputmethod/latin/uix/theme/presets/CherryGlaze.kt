package org.futo.inputmethod.latin.uix.theme.presets

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.theme.ThemeOption
import org.futo.inputmethod.latin.uix.theme.selector.ThemePreview

private val CherryRed = Color(0xFFDE3163) // Cherry red
private val CovertSlate = Color(0xFF2F4F4F) // Dark slate gray (covert slander)
private val CherryLight = Color(0xFFFFB6C1) // Light cherry
private val SlateLight = Color(0xFF708090) // Light slate

private val md_theme_cherry_glaze_light_primary = CherryRed
private val md_theme_cherry_glaze_light_onPrimary = Color(0xFFFFFFFF)
private val md_theme_cherry_glaze_light_primaryContainer = CherryLight
private val md_theme_cherry_glaze_light_onPrimaryContainer = Color(0xFF3E0014)
private val md_theme_cherry_glaze_light_secondary = Color(0xFF74565F)
private val md_theme_cherry_glaze_light_onSecondary = Color(0xFFFFFFFF)
private val md_theme_cherry_glaze_light_secondaryContainer = Color(0xFFFFD9E2)
private val md_theme_cherry_glaze_light_onSecondaryContainer = Color(0xFF2B151C)
private val md_theme_cherry_glaze_light_tertiary = Color(0xFF7C5635)
private val md_theme_cherry_glaze_light_onTertiary = Color(0xFFFFFFFF)
private val md_theme_cherry_glaze_light_tertiaryContainer = Color(0xFFFFDCC1)
private val md_theme_cherry_glaze_light_onTertiaryContainer = Color(0xFF2E1500)
private val md_theme_cherry_glaze_light_error = Color(0xFFBA1A1A)
private val md_theme_cherry_glaze_light_errorContainer = Color(0xFFFFDAD6)
private val md_theme_cherry_glaze_light_onError = Color(0xFFFFFFFF)
private val md_theme_cherry_glaze_light_onErrorContainer = Color(0xFF410002)
private val md_theme_cherry_glaze_light_background = Color(0xFFFFFBFF)
private val md_theme_cherry_glaze_light_onBackground = Color(0xFF201A1B)
private val md_theme_cherry_glaze_light_surface = Color(0xFFFFFBFF)
private val md_theme_cherry_glaze_light_onSurface = Color(0xFF201A1B)
private val md_theme_cherry_glaze_light_surfaceVariant = Color(0xFFF2DDE1)
private val md_theme_cherry_glaze_light_onSurfaceVariant = CovertSlate
private val md_theme_cherry_glaze_light_outline = SlateLight
private val md_theme_cherry_glaze_light_inverseOnSurface = Color(0xFFFAEEEF)
private val md_theme_cherry_glaze_light_inverseSurface = Color(0xFF352F30)

private val md_theme_cherry_glaze_dark_primary = CherryLight
private val md_theme_cherry_glaze_dark_onPrimary = Color(0xFF5E0029)
private val md_theme_cherry_glaze_dark_primaryContainer = Color(0xFF83003C)
private val md_theme_cherry_glaze_dark_onPrimaryContainer = CherryLight
private val md_theme_cherry_glaze_dark_secondary = Color(0xFFE2BDC6)
private val md_theme_cherry_glaze_dark_onSecondary = Color(0xFF422931)
private val md_theme_cherry_glaze_dark_secondaryContainer = Color(0xFF5A3F47)
private val md_theme_cherry_glaze_dark_onSecondaryContainer = Color(0xFFFFD9E2)
private val md_theme_cherry_glaze_dark_tertiary = Color(0xFFEFBF9D)
private val md_theme_cherry_glaze_dark_onTertiary = Color(0xFF48290C)
private val md_theme_cherry_glaze_dark_tertiaryContainer = Color(0xFF633F20)
private val md_theme_cherry_glaze_dark_onTertiaryContainer = Color(0xFFFFDCC1)
private val md_theme_cherry_glaze_dark_error = Color(0xFFFFB4AB)
private val md_theme_cherry_glaze_dark_errorContainer = Color(0xFF93000A)
private val md_theme_cherry_glaze_dark_onError = Color(0xFF690005)
private val md_theme_cherry_glaze_dark_onErrorContainer = Color(0xFFFFDAD6)
private val md_theme_cherry_glaze_dark_background = CovertSlate
private val md_theme_cherry_glaze_dark_onBackground = Color(0xFFECDFE0)
private val md_theme_cherry_glaze_dark_surface = CovertSlate
private val md_theme_cherry_glaze_dark_onSurface = Color(0xFFECDFE0)
private val md_theme_cherry_glaze_dark_surfaceVariant = Color(0xFF514347)
private val md_theme_cherry_glaze_dark_onSurfaceVariant = Color(0xFFD5C2C6)
private val md_theme_cherry_glaze_dark_outline = Color(0xFF9E8C90)
private val md_theme_cherry_glaze_dark_inverseOnSurface = Color(0xFF201A1B)
private val md_theme_cherry_glaze_dark_inverseSurface = Color(0xFFECDFE0)

private val CherryGlazeLightScheme = lightColorScheme(
    primary = md_theme_cherry_glaze_light_primary,
    onPrimary = md_theme_cherry_glaze_light_onPrimary,
    primaryContainer = md_theme_cherry_glaze_light_primaryContainer,
    onPrimaryContainer = md_theme_cherry_glaze_light_onPrimaryContainer,
    secondary = md_theme_cherry_glaze_light_secondary,
    onSecondary = md_theme_cherry_glaze_light_onSecondary,
    secondaryContainer = md_theme_cherry_glaze_light_secondaryContainer,
    onSecondaryContainer = md_theme_cherry_glaze_light_onSecondaryContainer,
    tertiary = md_theme_cherry_glaze_light_tertiary,
    onTertiary = md_theme_cherry_glaze_light_onTertiary,
    tertiaryContainer = md_theme_cherry_glaze_light_tertiaryContainer,
    onTertiaryContainer = md_theme_cherry_glaze_light_onTertiaryContainer,
    error = md_theme_cherry_glaze_light_error,
    errorContainer = md_theme_cherry_glaze_light_errorContainer,
    onError = md_theme_cherry_glaze_light_onError,
    onErrorContainer = md_theme_cherry_glaze_light_onErrorContainer,
    background = md_theme_cherry_glaze_light_background,
    onBackground = md_theme_cherry_glaze_light_onBackground,
    surface = md_theme_cherry_glaze_light_surface,
    onSurface = md_theme_cherry_glaze_light_onSurface,
    surfaceVariant = md_theme_cherry_glaze_light_surfaceVariant,
    onSurfaceVariant = md_theme_cherry_glaze_light_onSurfaceVariant,
    outline = md_theme_cherry_glaze_light_outline,
    inverseOnSurface = md_theme_cherry_glaze_light_inverseOnSurface,
    inverseSurface = md_theme_cherry_glaze_light_inverseSurface,
)

private val CherryGlazeDarkScheme = darkColorScheme(
    primary = md_theme_cherry_glaze_dark_primary,
    onPrimary = md_theme_cherry_glaze_dark_onPrimary,
    primaryContainer = md_theme_cherry_glaze_dark_primaryContainer,
    onPrimaryContainer = md_theme_cherry_glaze_dark_onPrimaryContainer,
    secondary = md_theme_cherry_glaze_dark_secondary,
    onSecondary = md_theme_cherry_glaze_dark_onSecondary,
    secondaryContainer = md_theme_cherry_glaze_dark_secondaryContainer,
    onSecondaryContainer = md_theme_cherry_glaze_dark_onSecondaryContainer,
    tertiary = md_theme_cherry_glaze_dark_tertiary,
    onTertiary = md_theme_cherry_glaze_dark_onTertiary,
    tertiaryContainer = md_theme_cherry_glaze_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_cherry_glaze_dark_onTertiaryContainer,
    error = md_theme_cherry_glaze_dark_error,
    errorContainer = md_theme_cherry_glaze_dark_errorContainer,
    onError = md_theme_cherry_glaze_dark_onError,
    onErrorContainer = md_theme_cherry_glaze_dark_onErrorContainer,
    background = md_theme_cherry_glaze_dark_background,
    onBackground = md_theme_cherry_glaze_dark_onBackground,
    surface = md_theme_cherry_glaze_dark_surface,
    onSurface = md_theme_cherry_glaze_dark_onSurface,
    surfaceVariant = md_theme_cherry_glaze_dark_surfaceVariant,
    onSurfaceVariant = md_theme_cherry_glaze_dark_onSurfaceVariant,
    outline = md_theme_cherry_glaze_dark_outline,
    inverseOnSurface = md_theme_cherry_glaze_dark_inverseOnSurface,
    inverseSurface = md_theme_cherry_glaze_dark_inverseSurface,
)

val CherryGlaze = ThemeOption(
    dynamic = false,
    key = "CherryGlaze",
    name = R.string.cherry_glaze_theme_name,
    available = { true }
) {
    CherryGlazeLightScheme
}

@Composable
@Preview
private fun PreviewThemeCherryGlaze() {
    ThemePreview(CherryGlaze)
}
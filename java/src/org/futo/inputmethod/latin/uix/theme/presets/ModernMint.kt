package org.futo.inputmethod.latin.uix.theme.presets

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.wrapLightColorScheme
import org.futo.inputmethod.latin.uix.wrapDarkColorScheme
import org.futo.inputmethod.latin.uix.theme.ThemeOption
import org.futo.inputmethod.latin.uix.theme.selector.ThemePreview

private val ModernMint = Color(0xFF98FB98) // Pale green (mint)
private val CharcoalGray = Color(0xFF36454F) // Charcoal
private val MintLight = Color(0xFFE0FFE0) // Very light mint
private val CharcoalLight = Color(0xFF708090) // Light charcoal

private val md_theme_modern_mint_light_primary = ModernMint
private val md_theme_modern_mint_light_onPrimary = Color(0xFF003A00)
private val md_theme_modern_mint_light_primaryContainer = MintLight
private val md_theme_modern_mint_light_onPrimaryContainer = Color(0xFF002200)
private val md_theme_modern_mint_light_secondary = Color(0xFF526350)
private val md_theme_modern_mint_light_onSecondary = Color(0xFFFFFFFF)
private val md_theme_modern_mint_light_secondaryContainer = Color(0xFFD4E8D0)
private val md_theme_modern_mint_light_onSecondaryContainer = Color(0xFF101F10)
private val md_theme_modern_mint_light_tertiary = Color(0xFF39656B)
private val md_theme_modern_mint_light_onTertiary = Color(0xFFFFFFFF)
private val md_theme_modern_mint_light_tertiaryContainer = Color(0xFFBCEBF2)
private val md_theme_modern_mint_light_onTertiaryContainer = Color(0xFF001F23)
private val md_theme_modern_mint_light_error = Color(0xFFBA1A1A)
private val md_theme_modern_mint_light_errorContainer = Color(0xFFFFDAD6)
private val md_theme_modern_mint_light_onError = Color(0xFFFFFFFF)
private val md_theme_modern_mint_light_onErrorContainer = Color(0xFF410002)
private val md_theme_modern_mint_light_background = Color(0xFFFCFDF7)
private val md_theme_modern_mint_light_onBackground = Color(0xFF1A1C19)
private val md_theme_modern_mint_light_surface = Color(0xFFFCFDF7)
private val md_theme_modern_mint_light_onSurface = Color(0xFF1A1C19)
private val md_theme_modern_mint_light_surfaceVariant = Color(0xFFDEE5DB)
private val md_theme_modern_mint_light_onSurfaceVariant = CharcoalGray
private val md_theme_modern_mint_light_outline = CharcoalLight
private val md_theme_modern_mint_light_inverseOnSurface = Color(0xFFF0F1EC)
private val md_theme_modern_mint_light_inverseSurface = Color(0xFF2F312E)

private val md_theme_modern_mint_dark_primary = MintLight
private val md_theme_modern_mint_dark_onPrimary = Color(0xFF003A00)
private val md_theme_modern_mint_dark_primaryContainer = Color(0xFF005200)
private val md_theme_modern_mint_dark_onPrimaryContainer = MintLight
private val md_theme_modern_mint_dark_secondary = Color(0xFFB8CCB4)
private val md_theme_modern_mint_dark_onSecondary = Color(0xFF243425)
private val md_theme_modern_mint_dark_secondaryContainer = Color(0xFF3A4B3A)
private val md_theme_modern_mint_dark_onSecondaryContainer = Color(0xFFD4E8D0)
private val md_theme_modern_mint_dark_tertiary = Color(0xFFA1CFD6)
private val md_theme_modern_mint_dark_onTertiary = Color(0xFF00363C)
private val md_theme_modern_mint_dark_tertiaryContainer = Color(0xFF1F4D53)
private val md_theme_modern_mint_dark_onTertiaryContainer = Color(0xFFBCEBF2)
private val md_theme_modern_mint_dark_error = Color(0xFFFFB4AB)
private val md_theme_modern_mint_dark_errorContainer = Color(0xFF93000A)
private val md_theme_modern_mint_dark_onError = Color(0xFF690005)
private val md_theme_modern_mint_dark_onErrorContainer = Color(0xFFFFDAD6)
private val md_theme_modern_mint_dark_background = CharcoalGray
private val md_theme_modern_mint_dark_onBackground = Color(0xFFE2E3DE)
private val md_theme_modern_mint_dark_surface = CharcoalGray
private val md_theme_modern_mint_dark_onSurface = Color(0xFFE2E3DE)
private val md_theme_modern_mint_dark_surfaceVariant = Color(0xFF424940)
private val md_theme_modern_mint_dark_onSurfaceVariant = Color(0xFFC2C9BF)
private val md_theme_modern_mint_dark_outline = Color(0xFF8C9389)
private val md_theme_modern_mint_dark_inverseOnSurface = Color(0xFF1A1C19)
private val md_theme_modern_mint_dark_inverseSurface = Color(0xFFE2E3DE)

private val ModernMintLightScheme = lightColorScheme(
    primary = md_theme_modern_mint_light_primary,
    onPrimary = md_theme_modern_mint_light_onPrimary,
    primaryContainer = md_theme_modern_mint_light_primaryContainer,
    onPrimaryContainer = md_theme_modern_mint_light_onPrimaryContainer,
    secondary = md_theme_modern_mint_light_secondary,
    onSecondary = md_theme_modern_mint_light_onSecondary,
    secondaryContainer = md_theme_modern_mint_light_secondaryContainer,
    onSecondaryContainer = md_theme_modern_mint_light_onSecondaryContainer,
    tertiary = md_theme_modern_mint_light_tertiary,
    onTertiary = md_theme_modern_mint_light_onTertiary,
    tertiaryContainer = md_theme_modern_mint_light_tertiaryContainer,
    onTertiaryContainer = md_theme_modern_mint_light_onTertiaryContainer,
    error = md_theme_modern_mint_light_error,
    errorContainer = md_theme_modern_mint_light_errorContainer,
    onError = md_theme_modern_mint_light_onError,
    onErrorContainer = md_theme_modern_mint_light_onErrorContainer,
    background = md_theme_modern_mint_light_background,
    onBackground = md_theme_modern_mint_light_onBackground,
    surface = md_theme_modern_mint_light_surface,
    onSurface = md_theme_modern_mint_light_onSurface,
    surfaceVariant = md_theme_modern_mint_light_surfaceVariant,
    onSurfaceVariant = md_theme_modern_mint_light_onSurfaceVariant,
    outline = md_theme_modern_mint_light_outline,
    inverseOnSurface = md_theme_modern_mint_light_inverseOnSurface,
    inverseSurface = md_theme_modern_mint_light_inverseSurface,
)

private val ModernMintDarkScheme = darkColorScheme(
    primary = md_theme_modern_mint_dark_primary,
    onPrimary = md_theme_modern_mint_dark_onPrimary,
    primaryContainer = md_theme_modern_mint_dark_primaryContainer,
    onPrimaryContainer = md_theme_modern_mint_dark_onPrimaryContainer,
    secondary = md_theme_modern_mint_dark_secondary,
    onSecondary = md_theme_modern_mint_dark_onSecondary,
    secondaryContainer = md_theme_modern_mint_dark_secondaryContainer,
    onSecondaryContainer = md_theme_modern_mint_dark_onSecondaryContainer,
    tertiary = md_theme_modern_mint_dark_tertiary,
    onTertiary = md_theme_modern_mint_dark_onTertiary,
    tertiaryContainer = md_theme_modern_mint_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_modern_mint_dark_onTertiaryContainer,
    error = md_theme_modern_mint_dark_error,
    errorContainer = md_theme_modern_mint_dark_errorContainer,
    onError = md_theme_modern_mint_dark_onError,
    onErrorContainer = md_theme_modern_mint_dark_onErrorContainer,
    background = md_theme_modern_mint_dark_background,
    onBackground = md_theme_modern_mint_dark_onBackground,
    surface = md_theme_modern_mint_dark_surface,
    onSurface = md_theme_modern_mint_dark_onSurface,
    surfaceVariant = md_theme_modern_mint_dark_surfaceVariant,
    onSurfaceVariant = md_theme_modern_mint_dark_onSurfaceVariant,
    outline = md_theme_modern_mint_dark_outline,
    inverseOnSurface = md_theme_modern_mint_dark_inverseOnSurface,
    inverseSurface = md_theme_modern_mint_dark_inverseSurface,
)

val ModernMint = ThemeOption(
    dynamic = false,
    key = "ModernMint",
    name = R.string.modern_mint_theme_name,
    available = { true }
) {
    wrapLightColorScheme(ModernMintLightScheme)
}

@Composable
@Preview
private fun PreviewThemeModernMint() {
    ThemePreview(ModernMint)
}
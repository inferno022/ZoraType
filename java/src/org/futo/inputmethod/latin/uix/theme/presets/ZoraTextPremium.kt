package org.futo.inputmethod.latin.uix.theme.presets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.extendedDarkColorScheme
import org.futo.inputmethod.latin.uix.theme.ThemeOption
import org.futo.inputmethod.latin.uix.theme.selector.ThemePreview

private val darkScheme = extendedDarkColorScheme(
    primary = Color(0xFF38BDF8), // Sky 400
    onPrimary = Color(0xFF0F172A), // Slate 900
    primaryContainer = Color(0xFF1E293B), // Slate 800
    onPrimaryContainer = Color(0xFFE0F2FE),
    secondary = Color(0xFF7DD3FC),
    onSecondary = Color(0xFF0C4A6E),
    secondaryContainer = Color(0xFF1E293B),
    onSecondaryContainer = Color(0xFFBAE6FD),
    tertiary = Color(0xFF7CD4FD),
    onTertiary = Color(0xFF083344),
    tertiaryContainer = Color(0xFF155E75),
    onTertiaryContainer = Color(0xFFCFFAFE),
    error = Color(0xFFFCA5A5),
    onError = Color(0xFF7F1D1D),
    errorContainer = Color(0xFF991B1B),
    onErrorContainer = Color(0xFFFEE2E2),
    outline = Color(0xFF475569), // Slate 500
    outlineVariant = Color(0xFF334155), // Slate 700
    surface = Color(0xFF0F172A), // Slate 900
    onSurface = Color(0xFFF1F5F9), // Slate 100
    onSurfaceVariant = Color(0xFF94A3B8), // Slate 400
    surfaceContainerHighest = Color(0xFF1E293B),
    shadow = Color(0xFF000000).copy(alpha = 0.8f),
    keyboardSurface = Color(0xFF0F172A),
    keyboardSurfaceDim = Color(0xFF020617),
    keyboardContainer = Color(0xFF1E293B),
    keyboardContainerVariant = Color(0xFF111827),
    onKeyboardContainer = Color(0xFFF8FAFC),
    keyboardPress = Color(0xFF334155),
    keyboardFade0 = Color(0xFF0F172A),
    keyboardFade1 = Color(0xFF0F172A),
    primaryTransparent = Color(0xFF38BDF8).copy(alpha = 0.15f),
    onSurfaceTransparent = Color(0xFFF1F5F9).copy(alpha = 0.1f),
)

val ZoraTextPremium = ThemeOption(
    dynamic = false,
    key = "ZoraTextPremium",
    name = R.string.theme_zoratext_premium,
    available = { true }
) {
    darkScheme
}

@Composable
@Preview
private fun PreviewTheme() {
    ThemePreview(ZoraTextPremium)
}

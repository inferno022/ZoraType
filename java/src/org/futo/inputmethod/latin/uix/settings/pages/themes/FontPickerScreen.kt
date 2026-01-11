package org.futo.inputmethod.latin.uix.settings.pages.themes

import android.graphics.Typeface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.runBlocking
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.KEYBOARD_FONT_KEY
import org.futo.inputmethod.latin.uix.setSetting
import org.futo.inputmethod.latin.uix.settings.ScreenTitle
import org.futo.inputmethod.latin.uix.settings.ScrollableList
import org.futo.inputmethod.latin.uix.settings.SettingItem
import org.futo.inputmethod.latin.uix.settings.useDataStore
import org.futo.inputmethod.latin.uix.theme.Typography

@Composable
fun FontPickerScreen(navController: NavHostController) {
    val context = LocalContext.current
    val currentFont = useDataStore(KEYBOARD_FONT_KEY.key, KEYBOARD_FONT_KEY.default).value

    val fonts = remember {
        val list = context.assets.list("fonts") ?: arrayOf()
        list.filter { it.endsWith(".ttf") || it.endsWith(".otf") }.sorted()
    }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            ScreenTitle(stringResource(R.string.settings_font_picker_title), showBack = true, navController)
            ScrollableList {
                // Default option
                FontItem(
                    name = stringResource(R.string.settings_font_none),
                    fileName = "",
                    isSelected = currentFont == "",
                    onClick = {
                        runBlocking { context.setSetting(KEYBOARD_FONT_KEY, "") }
                    }
                )

                fonts.forEach { fontFile ->
                    FontItem(
                        name = fontFile.substringBeforeLast("."),
                        fileName = fontFile,
                        isSelected = currentFont == fontFile,
                        onClick = {
                            runBlocking { context.setSetting(KEYBOARD_FONT_KEY, fontFile) }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun FontItem(name: String, fileName: String, isSelected: Boolean, onClick: () -> Unit) {
    val context = LocalContext.current
    val typeface = remember(fileName) {
        if (fileName.isEmpty()) null
        else {
            try {
                Typeface.createFromAsset(context.assets, "fonts/$fileName")
            } catch (e: Exception) {
                null
            }
        }
    }

    SettingItem(
        title = name,
        onClick = onClick,
        icon = {
            RadioButton(selected = isSelected, onClick = null)
        },
        modifier = Modifier.clearAndSetSemantics {
            this.contentDescription = name
            this.role = Role.RadioButton
            this.selected = isSelected
        }
    ) {
        if (typeface != null) {
            Text(
                text = "Abc",
                style = Typography.Body.Medium.copy(
                    fontFamily = FontFamily(typeface)
                ),
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

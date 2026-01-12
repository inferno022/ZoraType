package org.futo.inputmethod.latin.uix.settings.pages.pdict

import androidx.compose.runtime.Composable
import org.futo.inputmethod.latin.R
import org.futo.inputmethod.latin.uix.PersonalWord
import org.futo.inputmethod.latin.uix.settings.NavigationItem
import org.futo.inputmethod.latin.uix.settings.NavigationItemStyle
import java.util.Locale

// Stub implementation when MOZC AAR is not available
val PersonalDictionaryJaScreen = NavigationItem(
    title = R.string.personal_dictionary_ja,
    style = NavigationItemStyle.MissingFeature,
    screen = { @Composable {} }
)

// Stub data class when MOZC AAR is not available
data class JapanesePersonalWord(
    val furigana: String,
    val output: String,
    val pos: JapanesePartOfSpeech
)

// Stub enum when MOZC AAR is not available
enum class JapanesePartOfSpeech(val id: Int, val displayName: String) {
    NOUN(0, "Noun")
}

// Stub function when MOZC AAR is not available
fun decodeJapanesePersonalWord(word: PersonalWord): JapanesePersonalWord? = null

// Stub composable when MOZC AAR is not available
@Composable
fun JapaneseWordPopupDialog(selectedWord: JapanesePersonalWord? = null, locale: Locale? = Locale.JAPANESE) {}
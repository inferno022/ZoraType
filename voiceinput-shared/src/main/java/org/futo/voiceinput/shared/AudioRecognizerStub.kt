package org.futo.voiceinput.shared

import android.content.Context
import androidx.lifecycle.LifecycleCoroutineScope
import org.futo.voiceinput.shared.types.AudioRecognizerListener
import org.futo.voiceinput.shared.whisper.DecodingConfiguration
import org.futo.voiceinput.shared.whisper.ModelManager
import org.futo.voiceinput.shared.whisper.MultiModelRunConfiguration

// Stub data classes when VAD AAR is not available
data class RecordingSettings(
    val preferBluetoothMic: Boolean,
    val requestAudioFocus: Boolean,
    val canExpandSpace: Boolean,
    val useVADAutoStop: Boolean
)

data class AudioRecognizerSettings(
    val modelRunConfiguration: MultiModelRunConfiguration,
    val decodingConfiguration: DecodingConfiguration,
    val recordingConfiguration: RecordingSettings
)

class ModelDoesNotExistException(val models: List<Any>) : Throwable()

// Stub implementation when VAD AAR is not available
class AudioRecognizer(
    private val context: Context,
    private val lifecycleScope: LifecycleCoroutineScope,
    modelManager: ModelManager,
    private val listener: AudioRecognizerListener,
    private val settings: AudioRecognizerSettings
) {
    fun reset() {}
    fun finish() {}
    fun cancel() {}
    fun openPermissionSettings() {}
    fun start() {}
}
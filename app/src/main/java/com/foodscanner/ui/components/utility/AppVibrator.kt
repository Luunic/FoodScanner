package com.foodscanner.ui.components.utility

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppVibrator(
    private val context: Context,
    private val scope: CoroutineScope
) {
    fun vibrate(
        delayMillis: Long = 0L,
        durationMillis: Long = 40L,
        amplitude: Int = -1
    ) {
        scope.launch {
            if (delayMillis > 0L) {
                delay(delayMillis)
            }

            val vibrator = getVibrator() ?: return@launch

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        durationMillis,
                        if (amplitude == -1) {
                            VibrationEffect.DEFAULT_AMPLITUDE
                        } else {
                            amplitude
                        }
                    )
                )
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(durationMillis)
            }
        }
    }

    private fun getVibrator(): Vibrator? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager

            vibratorManager?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }
    }
}

@Composable
fun rememberAppVibrator(): AppVibrator {
    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()

    return remember(context, scope) {
        AppVibrator(
            context = context,
            scope = scope
        )
    }
}
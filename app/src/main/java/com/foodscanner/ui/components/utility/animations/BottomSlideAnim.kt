package com.foodscanner.ui.components.utility.animations

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

//animation for flying in everything but .customShadow elements
@Composable
fun SlideInFromBottom(
    visible: Boolean,
    delayMillis: Int = 0,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 450,
            delayMillis = delayMillis,
            easing = FastOutSlowInEasing
        ),
        label = "slideAlpha"
    )

    val translationY by animateFloatAsState(
        targetValue = if (visible) 0f else with(density) { 32.dp.toPx() },
        animationSpec = tween(
            durationMillis = 450,
            delayMillis = delayMillis,
            easing = FastOutSlowInEasing
        ),
        label = "slideTranslationY"
    )

    Box(
        modifier = Modifier.graphicsLayer {
            this.alpha = alpha
            this.translationY = translationY
        }
    ) {
        content()
    }
}
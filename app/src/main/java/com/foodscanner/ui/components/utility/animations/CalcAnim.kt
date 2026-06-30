package com.foodscanner.ui.components.utility.animations

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

data class BottomSlideAnimValues(
    val alpha: State<Float>,
    val translationY: State<Float>,
    val shadowAlpha: State<Float>
)

//animation for flying in .customShadow elements
@Composable
fun rememberBottomSlideAnimValues(
    visible: Boolean,
    delayMillis: Int = 0,
    startOffsetDp: Int = 32,
    maxShadowAlpha: Float = 0.22f
): BottomSlideAnimValues {
    val density = LocalDensity.current

    val alpha = animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 450,
            delayMillis = delayMillis,
            easing = FastOutSlowInEasing
        ),
        label = "bottomSlideAlpha"
    )

    val translationY = animateFloatAsState(
        targetValue = if (visible) 0f else with(density) { startOffsetDp.dp.toPx() },
        animationSpec = tween(
            durationMillis = 450,
            delayMillis = delayMillis,
            easing = FastOutSlowInEasing
        ),
        label = "bottomSlideTranslationY"
    )

    val shadowAlpha = animateFloatAsState(
        targetValue = if (visible) maxShadowAlpha else 0f,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = delayMillis + 80,
            easing = FastOutSlowInEasing
        ),
        label = "bottomSlideShadowAlpha"
    )

    return BottomSlideAnimValues(
        alpha = alpha,
        translationY = translationY,
        shadowAlpha = shadowAlpha
    )
}
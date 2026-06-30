package com.foodscanner.ui.components.utility

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//animation for deleting history/favorite products
@Composable
fun SwipeDeleteAnim(
    isDeleting: Boolean,
    modifier: Modifier = Modifier,
    delayMillis: Int = 0,
    onDeleteAnimationFinished: () -> Unit = {},
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val targetOffsetX = if (isDeleting) {
            -(maxWidth + 80.dp)
        } else {
            0.dp
        }

        val offsetX by animateDpAsState(
            targetValue = targetOffsetX,
            animationSpec = tween(
                durationMillis = 300,
                delayMillis = delayMillis,
                easing = FastOutSlowInEasing
            ),
            label = "swipeDeleteOffsetX",
            finishedListener = {
                if (isDeleting) {
                    onDeleteAnimationFinished()
                }
            }
        )

        Box(
            modifier = Modifier.offset(x = offsetX)
        ) {
            content()
        }
    }
}
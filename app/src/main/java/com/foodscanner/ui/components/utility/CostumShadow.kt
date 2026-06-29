package com.foodscanner.ui.components.utility

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.customShadow(
    color: Color,
    blurRadius: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp
) = this.drawBehind {
    drawIntoCanvas { canvas ->
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        if (blurRadius > 0.dp) {
            frameworkPaint.maskFilter = BlurMaskFilter(
                blurRadius.toPx(),
                BlurMaskFilter.Blur.NORMAL
            )
        }
        frameworkPaint.color = color.toArgb()

        val left = offsetX.toPx()
        val right = size.width + offsetX.toPx()
        val top = offsetY.toPx()
        val bottom = size.height + offsetY.toPx()

        canvas.drawRect(
            left = left,
            top = top,
            right = right,
            bottom = bottom,
            paint = paint
        )
    }
}
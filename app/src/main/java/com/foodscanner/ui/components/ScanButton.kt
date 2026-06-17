package com.foodscanner.ui.components

import android.content.Context
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp

// Creates Scan Button
@Composable
fun CreateScanButton (
    modifier: Modifier = Modifier,
    onScanRequested: () -> Unit
) {

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")

    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2200,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val waveSize = lerp(200.dp, 320.dp, progress)
    val waveAlpha = 0.50f * (1f - progress)

    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        Box(
            modifier = Modifier
                .size(waveSize)
                .clip(CircleShape)
                .background(Color(0xFF407E7D).copy(alpha = waveAlpha))
        )

        Button(
            modifier = Modifier
                .size(230.dp)
                .clip(CircleShape),
            onClick = { onScanRequested() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF407E7D)
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
//                Icon(
//                    imageVector = Icons.Default.CenterFocusStrong,
//                    contentDescription = "Scan",
//                    modifier = Modifier.size(58.dp)
//                )
                Text(
                    text = "TAP TO SCAN",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(14.dp)
                )
            }
        }
    }
}
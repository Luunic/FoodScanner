package com.foodscanner.ui.components.scanscreen

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.foodscanner.R

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
                Icon(painter = painterResource(R.drawable.center_focus_weak_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                    contentDescription = "Scan",
                    modifier = Modifier.size(58.dp),
                    tint = Color.White
              )
                Text(
                    text = stringResource(R.string.tap_to_scan),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .width(140.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}
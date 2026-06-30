package com.foodscanner.ui.components.productscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodscanner.R
import com.foodscanner.ui.components.utility.customShadow
import com.foodscanner.ui.components.utility.animations.rememberBottomSlideAnimValues

@Composable
fun HealthScoreCard(
    modifier: Modifier = Modifier,
    healthscorevalue: Int?,
    visible: Boolean = true,
    delayMillis: Int = 0
) {
    //fly in animation
    val anim = rememberBottomSlideAnimValues(
        visible = visible,
        delayMillis = delayMillis
    )

    //health score handling
    val healthScoreValueSafe = healthscorevalue ?: 0
    val healthScoreValueFraction = healthScoreValueSafe / 100f
    val rating = getHealthScoreRating(healthScoreValueSafe)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp)
            .customShadow(
                color = Color(70, 101, 101, (255 * anim.shadowAlpha.value).toInt()),
                blurRadius = 20.dp,
                offsetY = 8.dp
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    this.alpha = anim.alpha.value
                    this.translationY = anim.translationY.value
                },
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF407E7D)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.health_score),
                            color = Color.White.copy(alpha = 0.75f),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "$healthscorevalue/100",
                            color = Color.White,
                            fontSize = 40.sp,
                            lineHeight = 48.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(
                                color = Color.White.copy(alpha = 0.18f),
                                shape = RoundedCornerShape(18.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.verified_24dp_ffffff_fill0_wght400_grad0_opsz24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = rating,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.2f),
                            shape = CircleShape
                        )
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(healthScoreValueFraction)
                            .fillMaxHeight()
                            .background(
                                color = Color.White,
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}

//map health score to rating
@Composable
fun getHealthScoreRating(score: Int): String {
    val safeScore = score.coerceIn(0, 100)

    return when (safeScore) {
        in 90..100 -> stringResource(R.string.s100)
        in 80..89 -> stringResource(R.string.s89)
        in 70..79 -> stringResource(R.string.s79)
        in 60..69 -> stringResource(R.string.s69)
        in 50..59 -> stringResource(R.string.s59)
        in 40..49 -> stringResource(R.string.s49)
        in 30..39 -> stringResource(R.string.s39)
        in 20..29 -> stringResource(R.string.s29)
        in 10..19 -> stringResource(R.string.s19)
        else -> stringResource(R.string.s9)
    }
}
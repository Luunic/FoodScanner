package com.foodscanner.ui.components.productscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodscanner.R
import com.foodscanner.ui.components.utility.animations.rememberBottomSlideAnimValues
import com.foodscanner.ui.components.utility.customShadow

//shows if no current/last scanned product
@Composable
fun RedirectProductText(onButtonClick: () -> Unit) {

    //animation vars (old)
    var startAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        startAnimation = true
    }
    val textAnim = rememberBottomSlideAnimValues(
        visible = startAnimation,
        delayMillis = 0
    )
    val buttonAnim = rememberBottomSlideAnimValues(
        visible = startAnimation,
        delayMillis = 120
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .customShadow(
                        color = Color(
                            red = 70,
                            green = 101,
                            blue = 101,
                            alpha = (255 * textAnim.shadowAlpha.value).toInt()
                        ),
                        blurRadius = 20.dp,
                        offsetY = 8.dp
                    )
                    .graphicsLayer {
                        this.alpha = textAnim.alpha.value
                        this.translationY = textAnim.translationY.value
                    },
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 6.dp, bottom = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.first_scan_product),
                        fontSize = 24.sp,
                        letterSpacing = 1.sp,
                        lineHeight = 36.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF1A1C1C)
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            val cardShape = RoundedCornerShape(32.dp)

            Card(
                shape = cardShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .customShadow(
                        color = Color(
                            red = 70,
                            green = 101,
                            blue = 101,
                            alpha = (255 * buttonAnim.shadowAlpha.value).toInt()
                        ),
                        blurRadius = 20.dp,
                        offsetY = 8.dp
                    )
                    .graphicsLayer {
                        this.alpha = buttonAnim.alpha.value
                        this.translationY = buttonAnim.translationY.value
                    }
                    .clip(cardShape)
                    .clickable {
                        onButtonClick()
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF407E7D)
                ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.go_to_scanscreen),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }
    }
}
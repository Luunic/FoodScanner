package com.foodscanner.ui.components.productscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.foodscanner.R
import com.foodscanner.ui.components.utility.customShadow
import com.foodscanner.ui.components.utility.animations.rememberBottomSlideAnimValues

@Composable
fun ProductImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    visible: Boolean = true,
    delayMillis: Int = 0
) {
    //fly in animation
    val anim = rememberBottomSlideAnimValues(
        visible = visible,
        delayMillis = delayMillis
    )

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
                containerColor = Color.White
            )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.product_placeholder),
                error = painterResource(R.drawable.product_placeholder),
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
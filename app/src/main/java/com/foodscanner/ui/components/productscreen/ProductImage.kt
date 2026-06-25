package com.foodscanner.ui.components.productscreen

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.foodscanner.R
import com.foodscanner.ui.components.utility.customShadow

@Composable
fun ProductImage(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp)
            .customShadow(
                color = Color(70, 101, 101, (255 * 0.22f).toInt()),
                blurRadius = 20.dp,
                offsetY = 8.dp
            ),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Image(
            painter = painterResource(R.drawable.produc_placeholder),
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentScale = ContentScale.Fit
        )
    }
}
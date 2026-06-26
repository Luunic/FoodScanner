package com.foodscanner.ui.components.productscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodscanner.R
import com.foodscanner.ui.components.utility.customShadow

@Composable
fun ProductName(
    modifier: Modifier = Modifier,
    productName: String?
){
    Row (
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "$productName",
            modifier = Modifier.weight(1f),
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(modifier = Modifier.width(16.dp))

        Card(
            modifier = Modifier
                .size(62.dp)
                .customShadow(
                    color = Color(70, 101, 101, (255 * 0.22f).toInt()),
                    blurRadius = 20.dp,
                    offsetY = 8.dp
                ),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {
                //add product to favorites
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.favorite_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                    contentDescription = "Favorite",
                    tint = Color(0xFF246565),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

    }
}
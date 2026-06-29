package com.foodscanner.ui.components.historyscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.foodscanner.ui.components.utility.customShadow

@Composable
fun HistoryClearButton(
    modifier: Modifier = Modifier,
    onClearHistoryClick: () -> Unit
) {
    val cardShape = RoundedCornerShape(32.dp)

    Card(
        shape = cardShape,
        modifier = modifier
            .fillMaxWidth()
            .customShadow(
                color = Color(70, 101, 101, (255 * 0.22f).toInt()),
                blurRadius = 20.dp,
                offsetY = 8.dp
            )
            .clip(cardShape)
            .clickable {
                onClearHistoryClick()
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
                text = "Clear History",
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}
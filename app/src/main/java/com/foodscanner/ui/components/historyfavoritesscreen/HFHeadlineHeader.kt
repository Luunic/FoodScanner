package com.foodscanner.ui.components.historyfavoritesscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryScreenHeader(
    modifier: Modifier = Modifier,
    pagename: String,
    downwriting: String

) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = pagename,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.3).sp,
            color = Color(0xFF1A1C1C)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = downwriting,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF3F4948)
        )
    }
}
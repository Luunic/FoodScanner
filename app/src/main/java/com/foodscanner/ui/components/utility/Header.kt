package com.foodscanner.ui.components.utility

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VitalScanHeader(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .customShadow(
                color = Color(70, 101, 101, (255 * 0.22f).toInt()),
                blurRadius = 20.dp,
                offsetY = 8.dp
            )
            .background(Color.White.copy(alpha = 0.98f))
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "VitalScan",
            fontSize = 36.sp,
            fontWeight = Bold,
            color = Color(0xFF407E7D),
            modifier = Modifier.offset(y=4.dp)
        )
        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(y=4.dp)
        ) {
            Icon(
                painter = painterResource(com.foodscanner.R.drawable.settings_24dp),
                contentDescription = "Settings Icon",
                tint = Color(0xFF407E7D),
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
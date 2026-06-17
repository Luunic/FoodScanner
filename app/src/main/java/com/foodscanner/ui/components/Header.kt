package com.foodscanner.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// Creates Header + FX
@Composable
fun VitalScanHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp) // entspricht h-16 (16 * 4px = 64px)
            // 1. Shadow-Effekt: Custom Schatten passend zum HTML (rgba(36,101,101, 0.08))
            .customShadow(
                color = Color(70, 101, 101, (255 * 0.22f).toInt()),
                blurRadius = 20.dp,
                offsetY = 8.dp
            )
            // 2. Backdrop Blur: Weichzeichnen des darunterliegenden Inhalts
            .blur(
                // Wenn wir in der Preview sind, nutzen wir 0.dp (kein Blur),
                // auf dem echten Gerät die gewünschten 46.dp
                if (LocalInspectionMode.current) 0.dp else 46.dp
            )
            .background(Color.White.copy(alpha = 0.98f))
            // 5. Padding: Innenabstand links und rechts (px-5 -> 5 * 4px = 20px / dp)
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
    }
}
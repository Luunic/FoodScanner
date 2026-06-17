package com.foodscanner.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodscanner.R


// Creates Footer + FX
@Composable
fun VitalScanFooter(
    modifier: Modifier = Modifier,
    onScanClick: () -> Unit,
    onProductClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(84.dp) // entspricht h-16 (16 * 4px = 64px)
            // 1. Shadow-Effekt: Custom Schatten passend zum HTML (rgba(36,101,101, 0.08))
            .customShadow(
                color = Color(70, 101, 101, (255 * 0.22f).toInt()),
                blurRadius = 20.dp,
                offsetY = -8.dp
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
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .clickable { onScanClick() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = painterResource(R.drawable.barcode_scanner_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                    contentDescription = null,
                    modifier = Modifier.size(38.dp),
                    tint = Color(0xFF707675)
                )
                Text(
                    text = "Scan",
                    fontSize = 14.sp,
                    color = Color(0xFF707675)
                )
            }
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .clickable { onProductClick() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = painterResource(R.drawable.fastfood_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                    contentDescription = null,
                    modifier = Modifier.size(38.dp),
                    tint = Color(0xFF707675)
                )
                Text(
                    text = "Product",
                    fontSize = 14.sp,
                    color = Color(0xFF707675)
                )
            }
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .clickable { onHistoryClick() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = painterResource(R.drawable.history_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                    contentDescription = null,
                    modifier = Modifier.size(38.dp),
                    tint = Color(0xFF707675)
                )
                Text(
                    text = "History",
                    fontSize = 14.sp,
                    color = Color(0xFF707675)
                )
            }
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .clickable { onFavoritesClick() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = painterResource(R.drawable.favorite_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(38.dp)
                        .offset(x = (-2).dp),
                    tint = Color(0xFF707675)
                )
                Text(
                    text = "Favorites",
                    fontSize = 14.sp,
                    color = Color(0xFF707675)
                )
            }
        }
    }
}
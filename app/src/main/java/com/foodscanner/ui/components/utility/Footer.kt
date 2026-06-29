package com.foodscanner.ui.components.utility

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodscanner.R


//@Composable
//fun VitalScanFooter(
//    modifier: Modifier = Modifier,
//    currentRoute: String,
//    onScanClick: () -> Unit,
//    onProductClick: () -> Unit,
//    onHistoryClick: () -> Unit,
//    onFavoritesClick: () -> Unit
//) {
//    val selectedIndex = when (currentRoute) {
//        "scan" -> 0
//        "product" -> 1
//        "history" -> 2
//        "favorit" -> 3
//        else -> 0
//    }
//
//    Box(
//        modifier = modifier
//            .fillMaxWidth()
//            .navigationBarsPadding()
//            .height(84.dp)
//            .customShadow(
//                color = Color(70, 101, 101, (255 * 0.22f).toInt()),
//                blurRadius = 20.dp,
//                offsetY = -8.dp
//            )
//            .background(Color.White.copy(alpha = 0.98f))
//            .padding(horizontal = 20.dp),
//        contentAlignment = Alignment.Center,
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(84.dp),
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(
//                modifier = Modifier
//                    .width(80.dp)
//                    .clickable { onScanClick() },
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Icon(painter = painterResource(R.drawable.barcode_scanner_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
//                    contentDescription = null,
//                    modifier = Modifier.size(38.dp),
//                    tint = Color(0xFF707675)
//                )
//                Text(
//                    text = stringResource(R.string.scan),
//                    fontSize = 14.sp,
//                    color = Color(0xFF707675)
//                )
//            }
//            Column(
//                modifier = Modifier
//                    .width(80.dp)
//                    .clickable { onProductClick() },
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Icon(painter = painterResource(R.drawable.fastfood_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
//                    contentDescription = null,
//                    modifier = Modifier.size(38.dp),
//                    tint = Color(0xFF707675)
//                )
//                Text(
//                    text = stringResource(R.string.product),
//                    fontSize = 14.sp,
//                    color = Color(0xFF707675)
//                )
//            }
//            Column(
//                modifier = Modifier
//                    .width(80.dp)
//                    .clickable { onHistoryClick() },
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Icon(painter = painterResource(R.drawable.history_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
//                    contentDescription = null,
//                    modifier = Modifier.size(38.dp),
//                    tint = Color(0xFF707675)
//                )
//                Text(
//                    text = stringResource(R.string.history),
//                    fontSize = 14.sp,
//                    color = Color(0xFF707675)
//                )
//            }
//            Column(
//                modifier = Modifier
//                    .width(80.dp)
//                    .clickable { onFavoritesClick() },
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Icon(painter = painterResource(R.drawable.favorite_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(38.dp)
//                        .offset(x = (-2).dp),
//                    tint = Color(0xFF707675)
//                )
//                Text(
//                    text = stringResource(R.string.favorites),
//                    fontSize = 14.sp,
//                    color = Color(0xFF707675)
//                )
//            }
//        }
//    }
//}

@Composable
fun VitalScanFooter(
    modifier: Modifier = Modifier,
    currentRoute: String = "scan",
    onScanClick: () -> Unit,
    onProductClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    val selectedIndex = when (currentRoute) {
        "scan" -> 0
        "scanner" -> 0
        "product" -> 1
        "history" -> 2
        "favorit" -> 3
        else -> 0
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(84.dp)
            .customShadow(
                color = Color(70, 101, 101, (255 * 0.22f).toInt()),
                blurRadius = 20.dp,
                offsetY = (-8).dp
            )
            .background(Color.White.copy(alpha = 0.98f))
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        val itemWidth = maxWidth / 4

        val indicatorOffset by animateDpAsState(
            targetValue = itemWidth * selectedIndex,
            animationSpec = tween(durationMillis = 200),
            label = "footerIndicatorOffset"
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .offset(x = indicatorOffset)
                    .width(itemWidth)
                    .height(64.dp)
                    .padding(horizontal = 4.dp)
                    .background(
                        color = Color(0x33246565),
                        shape = RoundedCornerShape(20.dp)
                    )
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FooterItem(
                    modifier = Modifier.width(itemWidth),
                    selected = selectedIndex == 0,
                    iconRes = R.drawable.barcode_scanner_24dp_e3e3e3_fill0_wght400_grad0_opsz24,
                    label = stringResource(id = R.string.scan),
                    onClick = onScanClick
                )

                FooterItem(
                    modifier = Modifier.width(itemWidth),
                    selected = selectedIndex == 1,
                    iconRes = R.drawable.fastfood_24dp_e3e3e3_fill0_wght400_grad0_opsz24,
                    label = stringResource(id = R.string.product),
                    onClick = onProductClick
                )

                FooterItem(
                    modifier = Modifier.width(itemWidth),
                    selected = selectedIndex == 2,
                    iconRes = R.drawable.history_24dp_e3e3e3_fill0_wght400_grad0_opsz24,
                    label = stringResource(id = R.string.history),
                    onClick = onHistoryClick
                )

                FooterItem(
                    modifier = Modifier.width(itemWidth),
                    selected = selectedIndex == 3,
                    iconRes = R.drawable.favorite_24dp_e3e3e3_fill0_wght400_grad0_opsz24,
                    label = stringResource(id = R.string.favorites),
                    iconOffsetX = (-2).dp,
                    onClick = onFavoritesClick
                )
            }
        }
    }
}

@Composable
private fun FooterItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    iconRes: Int,
    label: String,
    iconOffsetX: Dp = 0.dp,
    onClick: () -> Unit
) {
    val contentColor by animateColorAsState(
        targetValue = if (selected) Color(0xFF246565) else Color(0xFF707675),
        animationSpec = tween(durationMillis = 200),
        label = "footerItemColor"
    )

    Column(
        modifier = modifier
            .height(64.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = label,
            modifier = Modifier
                .size(38.dp)
                .offset(x = iconOffsetX),
            tint = contentColor
        )

        Text(
            text = label,
            fontSize = 14.sp,
            color = contentColor
        )
    }
}
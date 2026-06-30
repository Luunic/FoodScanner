package com.foodscanner.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.foodscanner.FoodScannerViewModel
import com.foodscanner.R
import com.foodscanner.service.BarcodeScanner
import com.foodscanner.ui.components.utility.customShadow

@Composable
fun BarcodeScannerScreen(
    viewModel: FoodScannerViewModel,
    onBarcodeScanned: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .align(Alignment.Center)
                .padding(start = 24.dp, end = 24.dp, top = 114.dp, bottom = 152.dp)
                .customShadow(
                    color = Color(
                        red = 70,
                        green = 101,
                        blue = 101,
                        alpha = (255 * 0.22f).toInt()
                    ),
                    blurRadius = 20.dp,
                    offsetY = 8.dp
                ),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                BarcodeScanner(
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize(),
                    onBarcodeScanned = onBarcodeScanned
                )
                Icon(
                    painter = painterResource(id = R.drawable.crop_free_500dp),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(500.dp),
                    tint = Color.White.copy(alpha = 0.45f)
                )
            }
        }
    }
}
package com.foodscanner.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foodscanner.FoodScannerViewModel
import com.foodscanner.ui.components.CreateScanButton
import com.foodscanner.ui.components.GreetingText
import com.foodscanner.ui.components.VitalScanFooter
import com.foodscanner.ui.components.VitalScanHeader
import com.foodscanner.ui.theme.FoodScannerTheme


@Composable
fun ScanScreen(onScanRequested: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Box (
        modifier = Modifier.fillMaxSize()
    ) {
        CreateScanButton(modifier = Modifier
            .align(Alignment.Center)
            .offset( y = screenHeight * -0.04f),
            onScanRequested,
        )
        GreetingText(
            username = "Luis",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = screenHeight * 0.16f)
        )
        VitalScanHeader (
            modifier = Modifier.align(Alignment.TopCenter)
        )
        VitalScanFooter(
            modifier = Modifier.align(Alignment.BottomCenter),
            onScanClick = {},
            onProductClick = {},
            onHistoryClick = {},
            onFavoritesClick = {},
        )
    }
}

@Preview
@Composable
fun ScanScreenPreview() {
    FoodScannerTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF9F9F9)
        ){
            ScanScreen(onScanRequested = {})
        }
    }
}
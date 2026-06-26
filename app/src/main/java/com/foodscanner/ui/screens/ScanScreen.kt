package com.foodscanner.ui.screens

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
import com.foodscanner.ui.components.scanscreen.CreateScanButton
import com.foodscanner.ui.components.scanscreen.GreetingText
import com.foodscanner.ui.components.scanscreen.LastScannedBox
import com.foodscanner.ui.components.utility.VitalScanFooter
import com.foodscanner.ui.components.utility.VitalScanHeader
import com.foodscanner.ui.theme.FoodScannerTheme


@Composable
fun ScanScreen(
    onScanRequested: () -> Unit,
    onScanClick: () -> Unit,
    onProductClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Box (
        modifier = Modifier.fillMaxSize()
    ) {
        CreateScanButton(modifier = Modifier
            .align(Alignment.Center)
            .offset(y = screenHeight * -0.05f),
            onScanRequested,
        )
        GreetingText(
            username = "Luis",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = screenHeight * 0.16f)
        )
        LastScannedBox(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = screenHeight * 0.51f),
            productName = "Prinzenrolle",
            onClick = {
                // Navigate to Product Screen with last scanned product
            },
        )

//      Preview Header + Footer - disable when running app
//        VitalScanHeader(
//            modifier = Modifier.align(Alignment.TopCenter)
//        )
//        VitalScanFooter(
//            modifier = Modifier.align(Alignment.BottomCenter),
//            onScanClick = {},
//            onProductClick = {},
//            onHistoryClick = {},
//            onFavoritesClick = {}
//        )
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
            ScanScreen(
                onScanRequested = {},
                onScanClick = {},
                onProductClick = {},
                onHistoryClick = {},
                onFavoritesClick = {})
        }
    }
}
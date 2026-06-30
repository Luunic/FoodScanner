package com.foodscanner.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foodscanner.R
import com.foodscanner.data.Product
import com.foodscanner.ui.components.scanscreen.CreateScanButton
import com.foodscanner.ui.components.scanscreen.GreetingText
import com.foodscanner.ui.components.scanscreen.LastScannedBox
import com.foodscanner.ui.components.utility.animations.SlideInFromBottom
import com.foodscanner.ui.theme.FoodScannerTheme

@Composable
fun ScanScreen(
    username: String,
    lastScannedProduct: Product?,
    onScanRequested: () -> Unit,
    onLastScannedCLick: (Product?) -> Unit
) {
    val lastScannedName = lastScannedProduct?.getName()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    //animation state
    var startAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        startAnimation = true
    }


    //page layout
    Box (
        modifier = Modifier.fillMaxSize()
    ) {
        CreateScanButton(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = screenHeight * -0.05f),
            onScanRequested = onScanRequested,
            visible = startAnimation,
            delayMillis = 80
        )

        SlideInFromBottom(
            visible = startAnimation,
            delayMillis = 0
        ) {
            GreetingText(
                username = username,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = screenHeight * 0.16f)
            )
        }

        LastScannedBox(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = screenHeight * 0.51f),
            productName = lastScannedName ?: stringResource(R.string.no_product_scanned),
            visible = startAnimation,
            delayMillis = 160,
            onClick = {
                if (lastScannedProduct != null) {
                    onLastScannedCLick(lastScannedProduct)
                }
            },
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
            ScanScreen(
                onScanRequested = {},
                onLastScannedCLick = {},
                username = "Luis",
                lastScannedProduct = null
            )
        }
    }
}
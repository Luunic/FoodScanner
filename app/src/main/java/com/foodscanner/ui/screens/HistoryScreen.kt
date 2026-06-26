package com.foodscanner.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.foodscanner.ui.components.utility.VitalScanFooter
import com.foodscanner.ui.components.utility.VitalScanHeader
import com.foodscanner.ui.theme.FoodScannerTheme


@Composable
fun HistoryScreen(
    onScanClick: () -> Unit,
    onProductClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    Box (
        modifier = Modifier.fillMaxSize()
    ) {

        //Page yet to implement

        VitalScanHeader (
            modifier = Modifier.align(Alignment.TopCenter)
        )
        VitalScanFooter(
            modifier = Modifier.align(Alignment.BottomCenter),
            onScanClick = {onScanClick()},
            onProductClick = {onProductClick()},
            onHistoryClick = {onHistoryClick()},
            onFavoritesClick = {onFavoritesClick()}
        )
    }
}


//@Preview
//@Composable
//fun HistoryScreenPreview() {
//    FoodScannerTheme() {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = Color(0xFFF9F9F9)
//        ){
//            HistoryScreen()
//        }
//    }
//}
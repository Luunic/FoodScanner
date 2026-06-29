package com.foodscanner.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.foodscanner.FoodScannerViewModel
import com.foodscanner.service.BarcodeScanner

@Composable
fun BarcodeScannerScreen(
    viewModel: FoodScannerViewModel,
    onBarcodeScanned: (String) -> Unit
) {
    BarcodeScanner(
        viewModel = viewModel,
        modifier = Modifier.fillMaxSize(),
        onBarcodeScanned = onBarcodeScanned
    )
}
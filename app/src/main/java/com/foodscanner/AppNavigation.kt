package com.foodscanner

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.foodscanner.service.startBarcodeScanner
import com.foodscanner.ui.screens.ProductScreen
import com.foodscanner.ui.screens.ScanScreen

@Composable
fun StartApp(context: Context, viewModel: FoodScannerViewModel) {


    val productList by viewModel.historyState.collectAsState()
    val currentProduct by viewModel.currentProduct.collectAsState()
    val firstName = productList.firstOrNull()?.getName()
        ?.takeIf { it.isNotBlank() } ?: "Kein Produkt"
    var productNameList = mutableListOf<String?>()
    productList.forEach { product ->
        productNameList.add(0, product.getName())
    }
    //ScanScreen(onScanRequested = { startBarcodeScanner(context,viewModel) })
    ProductScreen()
}
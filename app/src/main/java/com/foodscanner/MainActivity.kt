package com.foodscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.foodscanner.storage.LocalStorage
import com.foodscanner.storage.ProductHistory
import com.foodscanner.ui.theme.FoodScannerTheme


class MainActivity : ComponentActivity() {
    private lateinit var controller: Controller
    private lateinit var viewModel: FoodScannerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val TAG = "MainActivity" // for logging
        controller =
            Controller(productHistory = ProductHistory(storage = LocalStorage(context = applicationContext)))

        viewModel = FoodScannerViewModel(controller)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(),
                Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color(0xFFFFFFFF).toArgb(),
                Color(0xFFFFFFFF).toArgb()
            )
        )
        setContent {
            FoodScannerTheme {
                StartApp(this, viewModel)
            }
        }
    }
}
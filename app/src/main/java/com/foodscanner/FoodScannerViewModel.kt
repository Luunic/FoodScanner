package com.foodscanner

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodscanner.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FoodScannerViewModel(private val controller: Controller) : ViewModel() {

    // Variables that hold the state
    val TAG = "ViewModel"
    private val _historyState = MutableStateFlow<List<Product>>(controller.getProductHistory())
    val historyState: StateFlow<List<Product>> = _historyState.asStateFlow() // read only!

    private val _currentProduct = MutableStateFlow<Product?>(null)
    val currentProduct: StateFlow<Product?> = _currentProduct.asStateFlow()

    fun scanBarcode(barcode: String) {
        viewModelScope.launch {
            val product = controller.getProductFromBarcode(barcode)
            _currentProduct.value = product
            _historyState.value = controller.getProductHistory()

            val HealthScoreDebug = product?.calculateHealthScore()
            Log.d(TAG, "HealthScore of ${product?.getName()}: $HealthScoreDebug")
        }
    }

    fun clearHistory() {
        controller.clearProductHistory()
        _historyState.value = controller.getProductHistory()
    }

    fun clearCurrentProduct() {
        _currentProduct.value = null
    }
}
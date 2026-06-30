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
    private val initialHistory = controller.getProductHistory()

    private val _historyState = MutableStateFlow<List<Product>>(initialHistory)
    val historyState: StateFlow<List<Product>> = _historyState.asStateFlow() // read only!

    private val _currentProduct = MutableStateFlow<Product?>(initialHistory.firstOrNull())
    val currentProduct: StateFlow<Product?> = _currentProduct.asStateFlow()

    private val _favoriteState = MutableStateFlow<List<Product>>(controller.getFavorites())
    val favoriteState: StateFlow<List<Product>> = _favoriteState.asStateFlow()

    fun scanBarcode(barcode: String) {
        viewModelScope.launch {
            val product = controller.getProductFromBarcode(barcode)
            _currentProduct.value = product
            _historyState.value = controller.getProductHistory()

            val HealthScoreDebug = product?.calculateHealthScore()
            Log.d(TAG, "HealthScore of ${product?.getName()}: $HealthScoreDebug")
        }
    }

    fun setCurrentProduct(product: Product?) {
        Log.d("CurrentProduct", "setCurrentProduct: ${product?.getName()}")
        _currentProduct.value = product
    }

    fun showLatestHistoryProduct() {
        _currentProduct.value = _historyState.value.firstOrNull()
    }

    fun clearCurrentProduct() {
        Log.d("CurrentProduct", "clearCurrentProduct")
        _currentProduct.value = null
    }

    fun clearHistory() {
        controller.clearProductHistory()
        _historyState.value = controller.getProductHistory()
        clearCurrentProduct()
    }

    fun deleteHistoryProduct(product: Product?) {
        controller.deleteHistoryProduct(product)

        val updatedHistory = controller.getProductHistory()
        _historyState.value = updatedHistory

        if (_currentProduct.value?.getCode() == product?.getCode()) {
            _currentProduct.value = updatedHistory.firstOrNull()
        }
    }

    fun addFavorite(product: Product?) {
        viewModelScope.launch {
            controller.addFavorite(product)
            _favoriteState.value = controller.getFavorites()
        }
    }

    fun deleteFavoriteProduct(product: Product?) {
        controller.deleteFavoriteProduct(product)
        _favoriteState.value = controller.getFavorites()
    }

    fun clearFavorites() {
        controller.clearFavorites()
        _favoriteState.value = controller.getFavorites()
    }

    fun toggleFavorite(product: Product?) {
        if (product == null) return

        val isAlreadyFavorite = _favoriteState.value.any {
            it.getCode() == product.getCode()
        }

        if (isAlreadyFavorite) {
            deleteFavoriteProduct(product)
        } else {
            addFavorite(product)
        }
    }
}
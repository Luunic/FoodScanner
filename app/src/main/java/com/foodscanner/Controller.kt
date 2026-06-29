package com.foodscanner

import android.util.Log
import com.foodscanner.data.Product
import com.foodscanner.service.OpenFoodFactsApi
import com.foodscanner.storage.ProductHistory
import com.foodscanner.service.ProductParser
import com.foodscanner.storage.Favorites
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Controller(
    private val productHistory: ProductHistory,
    private val favorites: Favorites,
) {
    val TAG = "Controller"

    private val _historyFlow = MutableStateFlow<List<Product>>(productHistory.getHistory())
    val historyFlow: StateFlow<List<Product>> = _historyFlow.asStateFlow()

    private val _favoriteFlow = MutableStateFlow<List<Product>>(favorites.getFavorites())
    val favoriteFlow: StateFlow<List<Product>> = _historyFlow.asStateFlow()

    private suspend fun getDataFromApi(barcode: String): JsonElement? {
        // Call the Api
        try {
            val response = OpenFoodFactsApi.retrofitService.getData(barcode)
            return Json.parseToJsonElement(response) // returns jsonElement
        } catch(e: Exception) {
            Log.e(TAG, "Exception $e while calling the Api")
         return null
        }
    }

    // Make this function private when its the time
    suspend fun getProductFromBarcode(barcode: String): Product? {
        val jsonData = getDataFromApi(barcode)

        if (jsonData != null) {
            // Add current date to Data

            val formatter = SimpleDateFormat("dd.MM.yyyy",Locale.getDefault())
            val dateString = formatter.format(Date())
            val extendedData = JsonObject(
                (jsonData as JsonObject) + mapOf(
                    "date" to JsonPrimitive(dateString)
                )
            )

            val parsed = ProductParser.parse(extendedData)

            Log.d("Controller", "${parsed.getLabels()}")

            productHistory.addProduct(extendedData)
            _historyFlow.value = productHistory.getHistory() // update
            return parsed
        }
        return null
    }

    fun getProductHistory(): List<Product> {
        return productHistory.getHistory()
    }
    fun clearProductHistory(): Boolean {
        val bool = productHistory.clearHistory()
        _historyFlow.value = emptyList() // update
        return bool
    }

    fun getFavorites(): List<Product> {
        return favorites.getFavorites()
    }
    fun clearFavorites(): Boolean {
        val bool = favorites.clearFavorites()
        _historyFlow.value = emptyList()
        return bool
    }

    fun deleteHistoryProduct(product: Product?): Boolean {
        return productHistory.deleteProduct(product)
    }
//    fun addFavorite(product: Product) {
//        favorites.addProduct(product)
//    }

}

package com.foodscanner

import com.foodscanner.data.Product
import com.foodscanner.service.OpenFoodFactsApi
import com.foodscanner.storage.LocalStorage
import com.foodscanner.storage.ProductHistory
import com.foodscanner.service.ProductParser
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Controller(
    private val productHistory: ProductHistory
) {

    private suspend fun getDataFromApi(barcode: String): JsonElement {
        // TODO refactor into try block
        // Call the Api

        val response = OpenFoodFactsApi.retrofitService.getData(barcode)

        return Json.parseToJsonElement(response) // returns jsonElement
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
            // Check if the product is already in History
            if (productHistory.getHistory().any {it.getCode() == barcode}) {
                return parsed
            }
            productHistory.addProduct(extendedData)
            return parsed
        }
        return null
    }

    fun getProductHistory(): List<Product> {
        return productHistory.getHistory()
    }
    fun clearProductHistory() {
        productHistory.clearHistory()
    }
}

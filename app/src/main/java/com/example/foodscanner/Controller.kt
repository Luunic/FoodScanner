package com.example.foodscanner

import com.example.foodscanner.data.Product
import com.example.foodscanner.service.OpenFoodFactsApi
import com.example.foodscanner.service.ProductParser.parse
import com.example.foodscanner.storage.LocalStorage
import com.example.foodscanner.storage.ProductHistory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Controller(
    private val productHistory: ProductHistory = ProductHistory(storage = LocalStorage())
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

            val parsed = parse(extendedData)

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

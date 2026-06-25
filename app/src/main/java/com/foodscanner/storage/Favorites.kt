package com.foodscanner.storage

import com.foodscanner.data.Product
import com.foodscanner.service.ProductParser
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class Favorites(private val storage: Storage) {

    val fileName = "favorites.json"

    fun addProduct(product: JsonElement) {
        val history = storage.loadStorage(fileName)

        // TODO Following two lines are messy and definily need to be refactored
        val barcode = product.jsonObject["product"]?.jsonObject?.get("code")?.jsonPrimitive?.content
        history.removeIf {it.jsonObject["product"]?.jsonObject?.get("code")?.jsonPrimitive?.content == barcode}

        history.add(0, product)

        if (history.size > storage.maxHistorySize) {
            history.removeAt(history.lastIndex)
        }
        storage.saveStorage(history, fileName)
    }

    fun getFavorites(): List<Product> {
        val history = storage.loadStorage(fileName)

        val list = mutableListOf<Product>()

        for (product in history) {
            list.add(ProductParser.parse(product))
        }
        return list
    }
    fun clearFavorites(): Boolean {
        var bool = true
        val history = storage.loadStorage(fileName)
        if(history.isEmpty()) bool = false
        history.clear()
        storage.saveStorage(history, fileName)
        return bool
    }


}
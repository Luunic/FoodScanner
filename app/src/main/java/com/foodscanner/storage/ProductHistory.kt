package com.foodscanner.storage

import com.foodscanner.data.Product
import com.foodscanner.service.ProductParser
import kotlinx.serialization.json.JsonElement

class ProductHistory(private val storage: Storage) {

    val fileName = "history.json"

    fun addProduct(product: JsonElement) {
        val history = storage.loadStorage(fileName)

        history.add(0, product)

        if (history.size > storage.maxHistorySize) {
            history.removeAt(history.lastIndex)
        }
        storage.saveStorage(history, fileName)

    }

    fun getHistory(): List<Product> {
        val history = storage.loadStorage(fileName)

        val list = mutableListOf<Product>()

        for (product in history) {
            list.add(ProductParser.parse(product))
        }
        return list
    }

    fun clearHistory(): Boolean {
        var bool = true
        val history = storage.loadStorage(fileName)
        if(history.isEmpty()) bool = false
        history.clear()
        storage.saveStorage(history, fileName)
        return bool
    }
}
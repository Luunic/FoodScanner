package com.foodscanner.storage

import com.foodscanner.data.Product
import com.foodscanner.service.ProductParser
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class Favorites(private val storage: Storage) {

    val fileName = "favorites.json"

    fun addProduct(product: JsonElement) {
        val favorites = storage.loadStorage(fileName)

        val barcode = product.jsonObject["product"]
            ?.jsonObject
            ?.get("code")
            ?.jsonPrimitive
            ?.content

        favorites.removeIf {
            it.jsonObject["product"]
                ?.jsonObject
                ?.get("code")
                ?.jsonPrimitive
                ?.content == barcode
        }

        favorites.add(0, product)

        storage.saveStorage(favorites, fileName)
    }

    fun getFavorites(): List<Product> {
        val favorites = storage.loadStorage(fileName)
        val list = mutableListOf<Product>()

        for (product in favorites) {
            list.add(ProductParser.parse(product))
        }

        return list
    }

    fun clearFavorites(): Boolean {
        val favorites = storage.loadStorage(fileName)
        if (favorites.isEmpty()) return false

        favorites.clear()
        storage.saveStorage(favorites, fileName)
        return true
    }

    fun deleteProduct(product: Product?): Boolean {
        if (product == null) return false

        val favorites = storage.loadStorage(fileName)
        val barcode = product.getCode()

        val removed = favorites.removeIf {
            it.jsonObject["product"]
                ?.jsonObject
                ?.get("code")
                ?.jsonPrimitive
                ?.content == barcode
        }

        storage.saveStorage(favorites, fileName)
        return removed
    }
}
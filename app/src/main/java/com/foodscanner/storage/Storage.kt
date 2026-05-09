package com.foodscanner.storage

import kotlinx.serialization.json.JsonElement

interface Storage {
    // Abstract interface for Storage
    val maxHistorySize: Int
        get() = 10

    fun loadStorage(fileName: String): MutableList<JsonElement>
    fun saveStorage(storage: MutableList<JsonElement>, fileName: String)
}
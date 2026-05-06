package com.example.foodscanner.storage

import kotlinx.serialization.json.JsonElement

interface Storage {
    // Abstract interface for Storage
    val maxHistorySize: Int
        get() = 10

    fun loadHistory(fileName: String): MutableList<JsonElement>
    fun saveHistory(history: MutableList<JsonElement>, fileName: String)
}
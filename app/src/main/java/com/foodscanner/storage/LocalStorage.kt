package com.foodscanner.storage

import kotlinx.serialization.json.JsonElement

class LocalStorage: Storage {

    override fun loadHistory(fileName: String): MutableList<JsonElement> {

        return mutableListOf<JsonElement>()
    }

    override fun saveHistory(history: MutableList<JsonElement>, fileName: String) {

    }
}
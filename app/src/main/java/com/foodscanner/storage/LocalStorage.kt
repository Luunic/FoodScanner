package com.foodscanner.storage

import android.content.Context
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import java.io.File
import android.util.Log
class LocalStorage(private val context: Context): Storage {

    val TAG = "LocalStorage"

    private val parentFile = getFilePath() // get the origin File Path

    private fun getFilePath(): File {
        return context.filesDir
    }


    /**
     * Loads the Storage from [fileName].
     * @param fileName
     * @return A List of JsonElements.
     * If [fileName] doesn't exists returns empty list.
     * */

    override fun loadStorage(fileName: String): MutableList<JsonElement> {
        Log.d(TAG,"Start loadStorage")
        val storageFile = File(parentFile, fileName)
        Log.d(TAG,"StorageFile: $storageFile")
        if(storageFile.exists()) {
            Log.d(TAG, "$storageFile exists")
            val jsonArray = Json.parseToJsonElement(storageFile.readText()).jsonArray
            return jsonArray.toMutableList()
        }
        Log.d(TAG, "$storageFile doesnt exists")
        return mutableListOf<JsonElement>()

    }

    /**
     * Saves [storage] in [fileName]
     * @param [storage], [fileName]
     * @return None
     * */

    override fun saveStorage(storage: MutableList<JsonElement>, fileName: String) {
        val storageFile = File(parentFile, fileName)
        storageFile.createNewFile() // Creates file if doesnt exist
        val jsonArray = JsonArray(storage)

        storageFile.writeText(jsonArray.toString()) // Write to the File

    }
}
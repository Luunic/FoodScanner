package com.foodscanner.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userSettingsDataStore by preferencesDataStore(
    name = "user_settings"
)

class UserSettingsStorage(
    private val context: Context
) {
    //declaration
    private val usernameKey = stringPreferencesKey("username")
    private val allergensKey = stringSetPreferencesKey("allergens")

    //read function
    val username: Flow<String> = context.userSettingsDataStore.data.map { preferences ->
        preferences[usernameKey] ?: ""
    }
    val allergens: Flow<List<String>> = context.userSettingsDataStore.data.map { preferences ->
        preferences[allergensKey]?.toList() ?: emptyList()
    }

    //storage function
    suspend fun saveUsername(username: String) {
        context.userSettingsDataStore.edit { preferences ->
            preferences[usernameKey] = username
        }
    }
    suspend fun saveAllergens(allergens: List<String>) {
        context.userSettingsDataStore.edit { preferences ->
            preferences[allergensKey] = allergens.toSet()
        }
    }
}
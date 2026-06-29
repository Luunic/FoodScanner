package com.foodscanner.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userSettingsDataStore by preferencesDataStore(
    name = "user_settings"
)

class UserSettingsStorage(
    private val context: Context
) {
    private val usernameKey = stringPreferencesKey("username")

    val username: Flow<String> = context.userSettingsDataStore.data.map { preferences ->
        preferences[usernameKey] ?: ""
    }

    suspend fun saveUsername(username: String) {
        context.userSettingsDataStore.edit { preferences ->
            preferences[usernameKey] = username
        }
    }
}
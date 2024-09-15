package com.example.autohub.data.storage

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsStorageImpl(private val context: Context) : SettingsStorage {

    private val darkThemeKey = booleanPreferencesKey(DARK_THEME_ENABLED_KEY)

    private val isDarkThemeEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[darkThemeKey] ?: false
        }

    override fun getAppTheme(): Flow<Boolean> = isDarkThemeEnabled

    override suspend fun changeAppTheme(isChecked: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[darkThemeKey] = isChecked
        }
    }

    companion object {
        const val DARK_THEME_ENABLED_KEY = "isDarkThemeEnabled"
    }
}
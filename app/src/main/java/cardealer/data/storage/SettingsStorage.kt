package com.example.autohub.data.storage

import kotlinx.coroutines.flow.Flow

interface SettingsStorage {
    fun getAppTheme(): Flow<Boolean>
    suspend fun changeAppTheme(isChecked: Boolean)
}
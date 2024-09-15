package com.example.autohub.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getAppTheme(): Flow<Boolean>
    suspend fun changeAppTheme(isChecked: Boolean)
}
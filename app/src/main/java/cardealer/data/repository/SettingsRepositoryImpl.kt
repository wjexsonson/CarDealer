package com.example.autohub.data.repository

import com.example.autohub.data.storage.SettingsStorage
import com.example.autohub.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(private val settingsStorage: SettingsStorage) : SettingsRepository {
    override fun getAppTheme(): Flow<Boolean> = settingsStorage.getAppTheme()

    override suspend fun changeAppTheme(isChecked: Boolean) =
        settingsStorage.changeAppTheme(isChecked)
}
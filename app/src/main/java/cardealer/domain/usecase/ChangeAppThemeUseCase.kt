package com.example.autohub.domain.usecase

import com.example.autohub.domain.repository.SettingsRepository

class ChangeAppThemeUseCase(private val settingsRepository: SettingsRepository) {
    suspend fun execute(isChecked: Boolean) = settingsRepository.changeAppTheme(isChecked)
}
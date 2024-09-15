package com.example.autohub.domain.usecase

import com.example.autohub.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetAppThemeUseCase(private val settingsRepository: SettingsRepository) {
    fun execute(): Flow<Boolean> = settingsRepository.getAppTheme()
}
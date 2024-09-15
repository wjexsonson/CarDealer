package com.example.autohub.domain.usecase

import com.example.autohub.domain.repository.SearchHistoryRepository

class ClearSearchHistoryUseCase(private val searchHistoryRepository: SearchHistoryRepository) {
    suspend fun execute() {
        searchHistoryRepository.clearSearchHistory()
    }
}
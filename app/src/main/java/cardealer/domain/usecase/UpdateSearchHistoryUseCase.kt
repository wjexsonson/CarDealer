package com.example.autohub.domain.usecase

import com.example.autohub.domain.repository.SearchHistoryRepository

class UpdateSearchHistoryUseCase(private val searchHistoryRepository: SearchHistoryRepository) {
    suspend fun execute(query: String) {
        searchHistoryRepository.updateSearchHistory(query)
    }
}
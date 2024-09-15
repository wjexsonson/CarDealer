package com.example.autohub.domain.usecase

import com.example.autohub.domain.model.SearchHistoryVo
import com.example.autohub.domain.repository.SearchHistoryRepository

class LoadSearchHistoryUseCase(private val searchHistoryRepository: SearchHistoryRepository) {
    suspend fun execute(): List<SearchHistoryVo> {
        return searchHistoryRepository.loadSearchHistory()
    }
}
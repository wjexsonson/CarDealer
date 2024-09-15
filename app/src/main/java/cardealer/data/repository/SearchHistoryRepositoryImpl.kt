package com.example.autohub.data.repository

import com.example.autohub.data.storage.SearchHistoryStorage
import com.example.autohub.data.storage.model.SearchHistoryDto
import com.example.autohub.domain.model.SearchHistoryVo
import com.example.autohub.domain.repository.SearchHistoryRepository

class SearchHistoryRepositoryImpl(private val searchHistoryStorage: SearchHistoryStorage) :
    SearchHistoryRepository {

    override suspend fun loadSearchHistory(): List<SearchHistoryVo> {
        val searchHistory = searchHistoryStorage.loadSearchHistory()
        return mapToDomain(searchHistory)
    }

    override suspend fun updateSearchHistory(query: String) {
        searchHistoryStorage.updateSearchHistory(query)
    }

    override suspend fun clearSearchHistory() {
        searchHistoryStorage.clearSearchHistory()
    }

    private fun mapToDomain(searchHistoryDtoList: List<SearchHistoryDto>): List<SearchHistoryVo> {
        val searchHistoryList = searchHistoryDtoList.map { searchHistoryItem ->
            SearchHistoryVo(
                query = searchHistoryItem.query
            )
        }
        return searchHistoryList
    }
}
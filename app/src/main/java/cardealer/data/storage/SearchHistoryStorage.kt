package com.example.autohub.data.storage

import com.example.autohub.data.storage.model.SearchHistoryDto

interface SearchHistoryStorage {
    suspend fun loadSearchHistory(): List<SearchHistoryDto>

    suspend fun updateSearchHistory(query: String)

    suspend fun clearSearchHistory()
}
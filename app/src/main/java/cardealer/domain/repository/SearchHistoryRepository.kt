package com.example.autohub.domain.repository

import com.example.autohub.domain.model.SearchHistoryVo

interface SearchHistoryRepository {
    suspend fun loadSearchHistory(): List<SearchHistoryVo>

    suspend fun updateSearchHistory(query: String)

    suspend fun clearSearchHistory()
}
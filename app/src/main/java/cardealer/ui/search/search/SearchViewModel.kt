package com.example.autohub.ui.search.search

import androidx.lifecycle.ViewModel
import com.example.autohub.domain.model.SearchHistoryVo
import com.example.autohub.domain.usecase.ClearSearchHistoryUseCase
import com.example.autohub.domain.usecase.LoadSearchHistoryUseCase
import com.example.autohub.domain.usecase.UpdateSearchHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel(
    private val loadSearchHistoryUseCase: LoadSearchHistoryUseCase,
    private val updateSearchHistoryUseCase: UpdateSearchHistoryUseCase,
    private val clearSearchHistoryUseCase: ClearSearchHistoryUseCase
) : ViewModel() {

    private val _searchHistory = MutableStateFlow<List<SearchHistoryVo>>(emptyList())
    val searchHistory: StateFlow<List<SearchHistoryVo>> = _searchHistory

    suspend fun loadSearchHistory() {
        _searchHistory.value = loadSearchHistoryUseCase.execute()
    }

    suspend fun updateSearchHistory(query: String) {
        updateSearchHistoryUseCase.execute(query)
        loadSearchHistory()
    }

    suspend fun clearSearchHistory() {
        clearSearchHistoryUseCase.execute()
        _searchHistory.value = emptyList()
    }
}

package com.example.autohub.ui.search.searchresults

import androidx.lifecycle.ViewModel
import com.example.autohub.domain.model.RecordsVo
import com.example.autohub.domain.usecase.SearchCarsByMakeUseCase
import com.example.autohub.ui.ScreenSwitchable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class SearchResultsViewModel(
    private val searchCarsByMakeUseCase: SearchCarsByMakeUseCase,
    private val screenSwitchable: ScreenSwitchable
) : ViewModel() {

    private var _searchResultsStateFlow = MutableStateFlow(RecordsVo(emptyList()))
    val searchResultsStateFlow: StateFlow<RecordsVo> = _searchResultsStateFlow

    suspend fun get(query: String) {
        withContext(Dispatchers.Main) {
            screenSwitchable.showProgressBar()
        }
        try {
            val records: RecordsVo = searchCarsByMakeUseCase.execute(query)
            _searchResultsStateFlow.value = records
            if (records.list.isEmpty()) {
                withContext(Dispatchers.Main) {
                    screenSwitchable.hideError()
                    screenSwitchable.showNoData()
                }
            } else {
                withContext(Dispatchers.Main) {
                    screenSwitchable.hideError()
                    screenSwitchable.showData()
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                _searchResultsStateFlow.value = RecordsVo(emptyList())
                screenSwitchable.showError()
            }
        } finally {
            withContext(Dispatchers.Main) {
                screenSwitchable.hideProgressBar()
            }
        }
    }
}
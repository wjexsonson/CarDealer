package com.example.autohub.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autohub.domain.model.RecordsVo
import com.example.autohub.domain.usecase.GetCarsUseCase
import com.example.autohub.domain.usecase.SortCarsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCarsUseCase: GetCarsUseCase,
    private val sortCarsUseCase: SortCarsUseCase,
) : ViewModel() {

    private val _carsStateFlow = MutableStateFlow(RecordsVo(emptyList()))
    val carsStateFlow: StateFlow<RecordsVo> = _carsStateFlow

    fun get() {
        viewModelScope.launch {
            try {
                val records: RecordsVo = getCarsUseCase.execute()
                _carsStateFlow.value = records
            } catch (e: Exception) {
                Log.e("ApiError", e.message.toString())
                _carsStateFlow.value = RecordsVo(emptyList())
            }
        }
    }

    fun sort(sortFilter: String) {
        viewModelScope.launch {
            try {
                val records: RecordsVo = sortCarsUseCase.execute(sortFilter)
                _carsStateFlow.value = records
            } catch (e: Exception) {
                Log.e("ApiError", e.message.toString())
                _carsStateFlow.value = RecordsVo(emptyList())
            }
        }
    }
}

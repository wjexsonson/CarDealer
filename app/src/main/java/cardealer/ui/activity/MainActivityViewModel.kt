package com.example.autohub.ui.activity

import androidx.lifecycle.ViewModel
import com.example.autohub.domain.usecase.GetAppThemeUseCase
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel(
    private val getAppThemeUseCase: GetAppThemeUseCase
) : ViewModel() {
    fun getAppTheme(): Flow<Boolean> = getAppThemeUseCase.execute()
}
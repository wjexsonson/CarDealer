package com.example.autohub.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autohub.domain.model.CarVo
import com.example.autohub.domain.usecase.GetCurrentUserUseCase
import com.example.autohub.domain.usecase.GetFavouritesUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> get() = _currentUser

    private val _carsStateFlow = MutableStateFlow<List<CarVo>>(emptyList())
    val carsStateFlow: StateFlow<List<CarVo>> get() = _carsStateFlow

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase.execute()
            _currentUser.collectLatest { user ->
                if (user == null) {
                    _carsStateFlow.value = emptyList()
                    return@collectLatest
                }
                getFavouritesUseCase.execute()
                    .collectLatest { favouriteList ->
                        _carsStateFlow.value = favouriteList
                    }
            }
        }
    }

    fun refreshCurrentUser() {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase.execute()
        }
    }
}

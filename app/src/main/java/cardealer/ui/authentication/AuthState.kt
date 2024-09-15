package com.example.autohub.ui.authentication

sealed class AuthState {
    data object Loading : AuthState()
    data object Success : AuthState()
    data object Error : AuthState()
}

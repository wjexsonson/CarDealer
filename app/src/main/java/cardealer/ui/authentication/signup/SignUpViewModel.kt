package com.example.autohub.ui.authentication.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.usecase.SignUpUseCase
import com.example.autohub.ui.authentication.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    fun signUpUser(userEmail: String, userPassword: String): StateFlow<AuthState> {
        val result = MutableStateFlow<AuthState>(AuthState.Loading)
        if (validateData(userEmail, userPassword)) {
            signUpUseCase.execute(userEmail, userPassword) { success ->
                result.value = if (success) {
                    AuthState.Success
                } else {
                    AuthState.Error
                }
            }
        }
        return result
    }

    private fun validateData(email: String, password: String): Boolean {
        return email.isNotEmpty() &&
                password.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
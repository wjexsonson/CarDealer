package com.example.autohub.ui.authentication.signin

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.autohub.domain.usecase.GetCurrentUserUseCase
import com.example.autohub.domain.usecase.SignInUseCase
import com.example.autohub.ui.authentication.AuthState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    fun signInUser(userEmail: String, userPassword: String): StateFlow<AuthState> {
        val result = MutableStateFlow<AuthState>(AuthState.Loading)
        if (validateData(userEmail, userPassword)) {
            signInUseCase.execute(userEmail, userPassword) { success ->
                result.value = if (success) {
                    AuthState.Success
                } else {
                    AuthState.Error
                }
            }
        } else {
            result.value = AuthState.Error
        }
        return result
    }


    fun getCurrentUser(): StateFlow<FirebaseUser?> {
        val user = MutableStateFlow<FirebaseUser?>(null)
        user.value = getCurrentUserUseCase.execute()
        return user
    }

    private fun validateData(email: String, password: String): Boolean {
        return email.isNotEmpty() &&
                password.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
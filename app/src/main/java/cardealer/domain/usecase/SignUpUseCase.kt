package com.example.autohub.domain.usecase

import com.example.autohub.domain.repository.AuthReposotory

class SignUpUseCase(private val authReposotory: AuthReposotory) {
    fun execute(email: String, password: String, callback: (Boolean) -> Unit) {
        authReposotory.signUp(email, password, callback)
    }
}
package com.example.autohub.domain.usecase

import com.example.autohub.domain.repository.AuthReposotory

class SignInUseCase(private val authReposotory: AuthReposotory) {
    fun execute(email: String, password: String, callback: (Boolean) -> Unit) {
        authReposotory.signIn(email, password, callback)
    }
}
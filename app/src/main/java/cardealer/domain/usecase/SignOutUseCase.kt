package com.example.autohub.domain.usecase

import com.example.autohub.domain.repository.AuthReposotory

class SignOutUseCase(private val authReposotory: AuthReposotory) {
    fun execute(){
        authReposotory.signOut()
    }
}
package com.example.autohub.domain.usecase

import com.example.autohub.domain.repository.AuthReposotory
import com.google.firebase.auth.FirebaseUser

class GetCurrentUserUseCase(private val authReposotory: AuthReposotory) {
    fun execute(): FirebaseUser? {
        return authReposotory.getCurrentUser()
    }
}
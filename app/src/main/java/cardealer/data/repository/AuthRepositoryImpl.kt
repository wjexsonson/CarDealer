package com.example.autohub.data.repository

import com.example.autohub.data.storage.AuthStorage
import com.example.autohub.domain.repository.AuthReposotory
import com.google.firebase.auth.FirebaseUser

class AuthRepositoryImpl(private val authStorage: AuthStorage) : AuthReposotory {
    override fun signUp(email: String, password: String, callback: (Boolean) -> Unit) {
        authStorage.signUp(email, password, callback)
    }

    override fun signIn(email: String, password: String, callback: (Boolean) -> Unit) {
        authStorage.signIn(email, password, callback)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return authStorage.getCurrentUser()
    }

    override fun signOut() {
        authStorage.signOut()
    }
}
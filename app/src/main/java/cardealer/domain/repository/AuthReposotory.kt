package com.example.autohub.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthReposotory {
    fun signUp(email: String, password: String, callback: (Boolean) -> Unit)
    fun signIn(email: String, password: String, callback: (Boolean) -> Unit)
    fun getCurrentUser() : FirebaseUser?
    fun signOut()
}
package com.example.autohub.data.storage

import com.google.firebase.auth.FirebaseUser

interface AuthStorage{
    fun signUp(email: String, password: String, callback: (Boolean) -> Unit)
    fun signIn(email: String, password: String, callback: (Boolean) -> Unit)
    fun getCurrentUser() : FirebaseUser?
    fun signOut()
}
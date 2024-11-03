package com.samsantech.souschef.viewmodel

import com.samsantech.souschef.firebase.FirebaseAuthManager
import com.samsantech.souschef.firebase.FirebaseUserManager
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel(
    private val firebaseAuthManager: FirebaseAuthManager,
    private val firebaseUserManager: FirebaseUserManager
) {
    fun signUp(username: String, email: String, password: String) {
        firebaseAuthManager.createNewUser(username, email, password)
    }
}
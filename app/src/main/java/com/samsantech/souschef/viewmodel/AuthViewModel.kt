package com.samsantech.souschef.viewmodel

import com.samsantech.souschef.data.User
import com.samsantech.souschef.firebase.FirebaseAuthManager
import com.samsantech.souschef.firebase.FirebaseUserManager
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel(
    private val firebaseAuthManager: FirebaseAuthManager,
    private val firebaseUserManager: FirebaseUserManager
) {
    val signUpInformation = MutableStateFlow<User>(User())

    fun login(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        firebaseAuthManager.login(email, password) { isSuccess, error ->
            onComplete(isSuccess, error)
        }
    }

    fun logout() {
        firebaseAuthManager.logout()
    }

    fun signUp(isSuccess: (Boolean) -> Unit) {
        firebaseAuthManager.signUp(signUpInformation.value) {
            isSuccess(it)
        }
    }

    fun setSignUpInformation(displayName: String? = null, username: String? = null, email: String? = null, password: String? = null) {
        signUpInformation.value = User(
            displayName = displayName?: signUpInformation.value.displayName,
            username = username?: signUpInformation.value.username,
            email = email?: signUpInformation.value.email,
            password = password?: signUpInformation.value.password
        )
    }

    fun changePassword(oldPassword: String, newPassword: String, callback: (Boolean, String?) -> Unit) {
        firebaseAuthManager.changePassword(oldPassword, newPassword) { isSuccess, error ->
            callback(isSuccess, error)
        }
    }

    fun resetPassword(email: String, callback: (Boolean, String?) -> Unit) {
        firebaseAuthManager.sendResetEmail(email) { isSuccess, error ->
            callback(isSuccess, error)
        }
    }
}
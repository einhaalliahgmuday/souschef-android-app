package com.samsantech.souschef.viewmodel

import com.google.firebase.auth.FirebaseUser
import com.samsantech.souschef.data.User
import com.samsantech.souschef.firebase.FirebaseUserManager

class UserViewModel(
    private val firebaseUserManager: FirebaseUserManager
) {
    fun getUser(uid: String, callback: (User) -> Unit) {
        firebaseUserManager.getUser(uid) { user ->
            if (user != null) {
                println(user)
                callback(user)
            }
        }
    }
}
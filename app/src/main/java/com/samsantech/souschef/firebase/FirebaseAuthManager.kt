package com.samsantech.souschef.firebase

import android.content.Context
import androidx.activity.ComponentActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class FirebaseAuthManager(
    private val auth: FirebaseAuth,
    private val firebaseUserManager: FirebaseUserManager
) {
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun createNewUser(username: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                firebaseUserManager.createUser(email, username)
            }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }
}
package com.samsantech.souschef.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.samsantech.souschef.data.User
import com.samsantech.souschef.data.UserPreferences

class FirebaseAuthManager(
    private val auth: FirebaseAuth,
    private val firebaseUserManager: FirebaseUserManager
) {
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun signUp(user: User, isSuccess: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                val signedUpUser = getCurrentUser()
                if (signedUpUser != null) {
                    firebaseUserManager.createUser(signedUpUser.uid, user.username) {
                        isSuccess(true)
                    }
                }
            }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }
}
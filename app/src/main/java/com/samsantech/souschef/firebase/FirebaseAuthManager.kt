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

    fun createNewUser(user: User, preferences: UserPreferences) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                val signedUpUser = getCurrentUser()
                if (signedUpUser != null) {
                    firebaseUserManager.createUser(signedUpUser.uid, user.username)
                    firebaseUserManager.createUserPreferences(signedUpUser.uid, preferences)
                }
            }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }
}
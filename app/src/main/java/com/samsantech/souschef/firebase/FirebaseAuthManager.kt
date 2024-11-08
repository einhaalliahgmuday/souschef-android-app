package com.samsantech.souschef.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.samsantech.souschef.data.User

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
                    firebaseUserManager.createUser(signedUpUser.uid, user.displayName, user.username, ) {
                        isSuccess(true)
                    }
                }
            }
    }

    fun login(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null)
                } else {
                    val exception = task.exception
                    onComplete(false, getErrorMessage(exception))
                }
            }
    }
}
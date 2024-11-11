package com.samsantech.souschef.firebase

import com.google.firebase.auth.EmailAuthProvider
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

    fun signUp(user: User, isSuccess: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener {
                var error: String? = null

                if (!it.isSuccessful) {
                    isSuccess(false, getErrorMessage(it.exception))
                    println(it.exception)
                } else {
                    val signedUpUser = getCurrentUser()
                    if (signedUpUser != null) {
                        firebaseUserManager.updateProfile(newDisplayName = user.displayName) { _, errorMessage ->
                            if (errorMessage != null) {
                                error = errorMessage
                            }
                        }
                        firebaseUserManager.createUser(signedUpUser.uid, user.email, user.username) {
                            isSuccess(true, error)
                        }
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

    fun logout() {
        auth.signOut()
    }

    fun changePassword(oldPassword: String, newPassword: String, callback: (Boolean, String?) -> Unit) {
        val user = getCurrentUser()

        if (user != null) {
            val credential = user.email?.let {
                EmailAuthProvider
                    .getCredential(it, oldPassword)
            }

            if (credential != null) {
                user.reauthenticate(credential)
                    .addOnCompleteListener{ task ->
                        if (!task.isSuccessful) {
                            callback(false, getErrorMessage(task.exception))
                        } else {
                            user.updatePassword(newPassword)
                                .addOnCompleteListener { updatePasswordTask ->
                                    if (!updatePasswordTask.isSuccessful) {
                                        callback(false, getErrorMessage(task.exception))
                                    } else {
                                        user.reload()
                                        callback(true, null)
                                    }
                                }
                        }
                    }
            }
        }
    }

    fun sendResetEmail(email: String, callback: (Boolean, String?) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, getErrorMessage(task.exception))
                }
            }
    }
}
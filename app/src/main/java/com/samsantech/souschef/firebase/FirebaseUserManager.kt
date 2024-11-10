package com.samsantech.souschef.firebase

import android.net.Uri
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.samsantech.souschef.data.User
import com.samsantech.souschef.data.UserPreferences

class FirebaseUserManager(private val auth: FirebaseAuth, private val db: FirebaseFirestore, private val storage: FirebaseStorage) {
    fun getUser(uid: String, callback: (User?) -> Unit) {
        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                if (user != null) {
                    callback(user)
                } else {
                    callback(null)
                }
            }
    }

    fun createUser(uid: String, email: String, username: String, isSuccess: (Boolean) -> Unit) {
        val user = hashMapOf(
            "username" to username,
            "email" to email
        )

        db.collection("users")
            .document(uid)
            .set(user)
            .addOnSuccessListener {
                isSuccess(true)
            }
    }

    fun updateUserPreferences(preferences: UserPreferences, isSuccess: (Boolean) -> Unit) {
        val user = auth.currentUser

        if (user != null) {
            db.collection("preferences")
                .document(user.uid)
                .set(preferences)
                .addOnSuccessListener {
                    isSuccess(true)
                    user.reload()
                }
        } else {
            isSuccess(false)
        }
    }

    fun updateProfile(username: String? = null, newDisplayName: String? = null, callback: (Boolean, String?) -> Unit) {
        val user = auth.currentUser

        if (user != null) {
            if (newDisplayName != null) {
                val profileUpdates = userProfileChangeRequest {
                    displayName = newDisplayName
                }
                user.updateProfile(profileUpdates)
                    .addOnCompleteListener {
                        if (!it.isSuccessful) {
                            callback(false, getErrorMessage(it.exception))
                        } else {
                            user.reload()
                        }
                    }

                if (username != null) {
                    val updatedUser = hashMapOf(
                        "username" to username,
                    )

                    db.collection("users")
                        .document(user.uid)
                        .set(updatedUser)
                        .addOnSuccessListener {
                            callback(true, null)
                            user.reload()
                        }
                }
            }
        }
    }

    fun updateProfilePhoto(imageUri: Uri, callback: (Boolean, String?) -> Unit) {
        val user = auth.currentUser

        if (user != null) {
            val storageRef = storage.reference
            val userProfileRef = storageRef.child("profile/${user.uid}.jpg")

            val uploadTask = userProfileRef.putFile(imageUri)

            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    callback(false, getErrorMessage(task.exception))
                }

                userProfileRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result

                    val profileUpdates = userProfileChangeRequest {
                        photoUri = Uri.parse("$downloadUri")
                    }
                    user.updateProfile(profileUpdates)
                        .addOnCompleteListener {
                            if (!it.isSuccessful) {
                                callback(true, getErrorMessage(task.exception))
                            } else {
                                user.reload()
                                callback(true, null)
                            }
                        }
                } else {
                    callback(true, getErrorMessage(task.exception))
                }
            }
        }
    }

    fun updateEmail(newEmail: String, password: String, callback: (Boolean, String?) -> Unit) {
        val user = auth.currentUser

        if (user != null) {
            val credential = user.email?.let {
                EmailAuthProvider
                    .getCredential(it, password)
            }

            if (credential != null) {
                user.reauthenticate(credential)
                    .addOnCompleteListener{ task ->
                        if (!task.isSuccessful) {
                            callback(false, getErrorMessage(task.exception))
                        } else {
                            user.updateEmail(newEmail)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        callback(true, null)
                                        user.reload()
                                    } else {
                                        callback(false, getErrorMessage(it.exception))
                                    }
                                }
                        }
                    }
            }
        }
    }

    fun isUsernameExists(username: String, isExists: (Boolean) -> Unit) {
        db.collection("users")
            .whereEqualTo("username", username)
            .limit(1)
            .get()
            .addOnSuccessListener {
                isExists(!it.isEmpty)
            }
    }

    fun isEmailExists(email: String, isExists: (Boolean) -> Unit) {
        db.collection("users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener {
                isExists(!it.isEmpty)
            }
    }
}
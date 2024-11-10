package com.samsantech.souschef.firebase

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.samsantech.souschef.data.User
import com.samsantech.souschef.data.UserPreferences

class FirebaseUserManager(private val auth: FirebaseAuth, private val db: FirebaseFirestore, private val storage: FirebaseStorage) {
    fun createUser(uid: String, displayName: String, username: String, isSuccess: (Boolean) -> Unit) {
        val user = hashMapOf(
            "displayName" to displayName,
            "username" to username
        )

        db.collection("users")
            .document(uid)
            .set(user)
            .addOnSuccessListener {
                isSuccess(true)
            }
    }

    fun createUserPreferences(preferences: UserPreferences, isSuccess: (Boolean) -> Unit) {
        val user = auth.currentUser

        if (user != null) {
            db.collection("preferences")
                .document(user.uid)
                .set(preferences)
                .addOnSuccessListener {
                    isSuccess(true)
                    println("saks")
                }

            println(user.uid)
        } else {
            isSuccess(false)
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
            .limit(1)
            .get()
            .addOnSuccessListener {
                isExists(!it.isEmpty)
            }
    }

    fun getUser(uid: String, callback: (User?) -> Unit) {
        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener {
                print("success")
                val user = it.toObject(User::class.java)
                if (user != null) {
                    callback(user)
                } else {
                    callback(null)
                }
            }
    }

    fun uploadUserProfilePicture(imageUri: Uri, callback: (Boolean, String?) -> Unit) {
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
                                callback(true, null)
                            }
                        }
                } else {
                    callback(true, getErrorMessage(task.exception))
                }
            }
        }
    }
}
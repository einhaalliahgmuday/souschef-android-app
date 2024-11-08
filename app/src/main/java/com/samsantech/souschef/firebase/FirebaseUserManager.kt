package com.samsantech.souschef.firebase

import com.google.firebase.auth.FirebaseAuth
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
}
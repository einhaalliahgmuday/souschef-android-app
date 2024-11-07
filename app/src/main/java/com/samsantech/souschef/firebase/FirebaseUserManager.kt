package com.samsantech.souschef.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.samsantech.souschef.data.UserPreferences

class FirebaseUserManager(private val db: FirebaseFirestore, private val storage: FirebaseStorage) {
    fun createUser(uid: String, username: String) {
        val user = hashMapOf(
            "uid" to uid,
            "username" to username
        )

        db.collection("users")
            .add(user)
    }

    fun createUserPreferences(uid: String, preferences: UserPreferences) {
        db.collection("preferences")
            .document(uid)
            .set(preferences)
    }

    fun getUserByUsername(username: String) {
        db.collection("users")
            .whereEqualTo("username", username)
            .limit(1)
            .get()
    }

    fun getUserByEmail(email: String) {
        db.collection("users")
            .whereEqualTo("username", email)
            .limit(1)
            .get()
    }
}
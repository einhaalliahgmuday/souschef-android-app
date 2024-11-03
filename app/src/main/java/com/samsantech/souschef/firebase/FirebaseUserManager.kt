package com.samsantech.souschef.firebase

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseUserManager(private val db: FirebaseFirestore) {
    fun createUser(email: String, username: String) {
        val user = hashMapOf(
            "email" to email,
            "username" to username
        )

        db.collection("users")
            .add(user)
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
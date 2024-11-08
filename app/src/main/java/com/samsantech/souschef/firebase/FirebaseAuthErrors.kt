package com.samsantech.souschef.firebase

fun getErrorMessage(exception: Exception?): String {
    return when (exception) {
        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> {
            "Invalid credentials. Please check your email and password."
        }
        else -> {
            "An error occurred. Please try again."
        }
    }
}
package com.samsantech.souschef.firebase

fun getErrorMessage(exception: Exception?): String {
    return when (exception) {
        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> {
            "Invalid credentials"
        }
        else -> {
            "An error occurred. Please try again."
        }
    }
}
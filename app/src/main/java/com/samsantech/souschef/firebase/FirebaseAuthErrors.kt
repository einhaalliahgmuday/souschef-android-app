package com.samsantech.souschef.firebase

fun getErrorMessage(exception: Exception?): String {
    return when (exception) {
        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> {
            "Invalid credentials"
        }
        is com.google.firebase.auth.FirebaseAuthInvalidUserException -> {
            "Account not found."
        }
        is com.google.firebase.FirebaseTooManyRequestsException -> {
            "Too many requests. Please try again later."
        }
        else -> {
            "An error occurred. Please try again."
        }
    }
}
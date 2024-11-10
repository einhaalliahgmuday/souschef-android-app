package com.samsantech.souschef.utils

fun isValidUsername(username: String): Boolean {
    val usernamePattern = "^[a-zA-Z0-9._]+$".toRegex()
    return username.matches(usernamePattern)
}
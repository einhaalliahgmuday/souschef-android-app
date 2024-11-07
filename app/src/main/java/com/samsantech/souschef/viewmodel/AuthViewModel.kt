package com.samsantech.souschef.viewmodel

import com.samsantech.souschef.data.User
import com.samsantech.souschef.data.UserPreferences
import com.samsantech.souschef.firebase.FirebaseAuthManager
import com.samsantech.souschef.firebase.FirebaseUserManager
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel(
    private val firebaseAuthManager: FirebaseAuthManager,
    private val firebaseUserManager: FirebaseUserManager
) {
    val signUpInformation = MutableStateFlow<User>(User())
    val signUpPreferences = MutableStateFlow<UserPreferences>(UserPreferences())
    val otherCuisine = MutableStateFlow<String>("")

    fun signUp() {
        signUpPreferences.value.cuisines = signUpPreferences.value.cuisines?.plus(otherCuisine.value)   //will this work?
        firebaseAuthManager.createNewUser(signUpInformation.value, signUpPreferences.value)
    }

    fun setSignUpInformation(username: String? = null, email: String? = null, password: String? = null) {
        signUpInformation.value = User(
            username = username?: signUpInformation.value.username,
            email = email?: signUpInformation.value.email,
            password = password?: signUpInformation.value.password
        )
    }

    fun addPreferencesCuisine(cuisine: String) {
        signUpPreferences.value = signUpPreferences.value.copy(
            cuisines = signUpPreferences.value.cuisines?.plus(cuisine) ?: listOf(cuisine)
        )
    }

    fun removePreferencesCuisine(cuisine: String) {
        signUpPreferences.value = signUpPreferences.value.copy(
            cuisines = signUpPreferences.value.cuisines?.minus(cuisine) ?: listOf(cuisine)
        )
    }
}
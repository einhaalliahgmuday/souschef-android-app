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

    fun login(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        firebaseAuthManager.login(email, password) { isSuccess, error ->
            onComplete(isSuccess, error)
        }
    }

    fun signUp(isSuccess: (Boolean) -> Unit) {
        firebaseAuthManager.signUp(signUpInformation.value) {
            isSuccess(it)
        }
    }

    fun changePassword(oldPassword: String, newPassword: String, callback: (Boolean, String?) -> Unit) {
        firebaseAuthManager.changePassword(oldPassword, newPassword) { isSuccess, error ->
            callback(isSuccess, error)
        }
    }

    fun resetPassword(email: String, callback: (Boolean, String?) -> Unit) {
        firebaseAuthManager.sendResetEmail(email) { isSuccess, error ->
            callback(isSuccess, error)
        }
    }

    fun setUserPreferences(isSuccess: (Boolean) -> Unit) {
        signUpPreferences.value.cuisines = signUpPreferences.value.cuisines?.plus(otherCuisine.value)
        firebaseUserManager.createUserPreferences(signUpPreferences.value) {
            isSuccess(it)
        }
    }

    fun setSignUpInformation(displayName: String? = null, username: String? = null, email: String? = null, password: String? = null) {
        signUpInformation.value = User(
            displayName = displayName?: signUpInformation.value.displayName,
            username = username?: signUpInformation.value.username,
            email = email?: signUpInformation.value.email,
            password = password?: signUpInformation.value.password
        )
    }

    fun isUsernameExists(username: String, isExists: (Boolean) -> Unit) {
        firebaseUserManager.isUsernameExists(username) {
            isExists(it)
        }
    }

    fun isEmailExists(email: String, isExists: (Boolean) -> Unit) {
        firebaseUserManager.isEmailExists(email) {
            isExists(it)
        }
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

    fun clearPreferencesCuisine() {
        signUpPreferences.value = signUpPreferences.value.copy(cuisines = listOf())
    }

    fun addPreferencesDislike(dislike: String) {
        signUpPreferences.value = signUpPreferences.value.copy(
            dislikes = signUpPreferences.value.dislikes?.plus(dislike) ?: listOf(dislike)
        )
    }

    fun removePreferencesDislike(dislike: String) {
        signUpPreferences.value = signUpPreferences.value.copy(
            dislikes = signUpPreferences.value.dislikes?.minus(dislike) ?: listOf(dislike)
        )
    }

    fun clearPreferencesDislikes() {
        signUpPreferences.value = signUpPreferences.value.copy(dislikes = listOf())
    }

    fun setPreferencesSkillLevel(skillLevel: String) {
        signUpPreferences.value = signUpPreferences.value.copy(
            skillLevel = skillLevel
        )
    }

    fun clearPreferencesSkillLevel() {
        signUpPreferences.value = signUpPreferences.value.copy(skillLevel = "")
    }
}
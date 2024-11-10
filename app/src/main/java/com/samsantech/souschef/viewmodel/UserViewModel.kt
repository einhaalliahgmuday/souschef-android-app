package com.samsantech.souschef.viewmodel

import android.net.Uri
import com.samsantech.souschef.data.User
import com.samsantech.souschef.firebase.FirebaseAuthManager
import com.samsantech.souschef.firebase.FirebaseUserManager
import kotlinx.coroutines.flow.MutableStateFlow

class UserViewModel(
    private val firebaseAuthManager: FirebaseAuthManager,
    private val firebaseUserManager: FirebaseUserManager
) {
    val user = MutableStateFlow<User>(User())

    init {
        refreshUser()
    }

    fun refreshUser() {
        val currentUser = firebaseAuthManager.getCurrentUser()

        if (currentUser != null) {
            firebaseUserManager.getUser(currentUser.uid) {
                if (it != null) {
                    user.value = currentUser.email?.let { email ->
                        User(
                            uid = currentUser.uid,
                            username = it.username,
                            email = email,
                            displayName = it.displayName,
                            photoUrl = currentUser.photoUrl.toString()
                        )
                    }!!
                }
            }
        }
    }

    fun setProfilePicture(imageUri: Uri, callback: (Boolean, String?) -> Unit) {
        firebaseUserManager.uploadUserProfilePicture(imageUri) { isSuccess, error ->
            if (isSuccess) {
                refreshUser()
            }

            callback(isSuccess, error)
        }
    }
}
package com.samsantech.souschef.data

import com.google.firebase.firestore.PropertyName

data class User(
    var uid: String = "",
    var displayName: String = "",
    var username: String = "",
    var email: String = "",
    var photo: String? = null,
    var password: String = ""
)

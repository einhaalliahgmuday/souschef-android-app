package com.samsantech.souschef.data

data class UserPreferences(
    var cuisines: List<String>? = null,
    var dislikes: List<String>? = null,
    var skillLevel: String = ""
)

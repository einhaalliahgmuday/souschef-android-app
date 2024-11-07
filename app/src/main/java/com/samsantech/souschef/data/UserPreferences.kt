package com.samsantech.souschef.data

data class UserPreferences(
    var cuisines: List<String>? = null,
    var dislikes: MutableList<String>? = null,
    var skillLevel: String = ""
)

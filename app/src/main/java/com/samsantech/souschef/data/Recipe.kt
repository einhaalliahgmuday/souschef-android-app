package com.samsantech.souschef.data

import android.net.Uri

data class Recipe(
    var photos: HashMap<String, Uri>? = null,
    var title: String = "",
    var description: String = "",
    var cookTimeHr: String = "",
    var cookTimeMin: String = "",
    var prepTimeHr: String = "",
    var prepTimeMin: String = "",
    var serving: String = "",
    var difficulty: String = "",
    var mealTypes: List<String>? = null,
    var categories: List<String>? = null,
    var ingredients: List<String>? = null,
    var instructions: List<String>? = null,
    var tags: List<String>? = null
)

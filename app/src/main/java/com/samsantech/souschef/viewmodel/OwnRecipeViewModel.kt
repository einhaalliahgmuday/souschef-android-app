package com.samsantech.souschef.viewmodel

import android.net.Uri
import com.samsantech.souschef.data.Recipe
import kotlinx.coroutines.flow.MutableStateFlow

class OwnRecipeViewModel {
    val newRecipe = MutableStateFlow<Recipe>(Recipe())

    fun addPhoto(key: String, value: Uri) {
        val photos = HashMap<String, Uri>(newRecipe.value.photos?.toMap() ?: hashMapOf())
        photos[key] = value
        newRecipe.value = newRecipe.value.copy(
            photos = photos
        )
    }

    fun removePhoto(key: String) {
        val photos = HashMap<String, Uri>(newRecipe.value.photos?.toMap() ?: hashMapOf())
        photos.remove(key)
        newRecipe.value = newRecipe.value.copy(
            photos = photos
        )
    }

    fun setTitle(title: String) {
        newRecipe.value = newRecipe.value.copy(
            title = title
        )
    }

    fun setDescription(description: String) {
        newRecipe.value = newRecipe.value.copy(
            description = description
        )
    }

    fun setPrepTimeHr(prepTimeHr: String) {
        newRecipe.value = newRecipe.value.copy(
            prepTimeHr = prepTimeHr
        )
    }

    fun setPrepTimeMin(prepTimeMin: String) {
        newRecipe.value = newRecipe.value.copy(
            prepTimeMin = prepTimeMin
        )
    }

    fun setCookTimeHr(cookTimeHr: String) {
        newRecipe.value = newRecipe.value.copy(
            cookTimeHr = cookTimeHr
        )
    }

    fun setCookTimeMin(cookTimeMin: String) {
        newRecipe.value = newRecipe.value.copy(
            cookTimeMin = cookTimeMin
        )
    }

    fun setServing(serving: String) {
        newRecipe.value = newRecipe.value.copy(
            serving = serving
        )
    }

    fun setDifficulty(difficulty: String) {
        newRecipe.value = newRecipe.value.copy(
            difficulty = difficulty
        )
    }

    fun addMealType(mealType: String) {
        newRecipe.value = newRecipe.value.copy(
            mealTypes = newRecipe.value.mealTypes.plus(mealType)
        )
    }

    fun removeMealType(mealType: String) {
        newRecipe.value = newRecipe.value.copy(
            mealTypes = newRecipe.value.mealTypes.minus(mealType)
        )
    }

    fun addCategory(category: String) {
        newRecipe.value = newRecipe.value.copy(
            categories = newRecipe.value.categories.plus(category)
        )
    }

    fun removeCategory(category: String) {
        newRecipe.value = newRecipe.value.copy(
            categories = newRecipe.value.categories.minus(category)
        )
    }
}
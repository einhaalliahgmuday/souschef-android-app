package com.samsantech.souschef.viewmodel

import android.net.Uri
import com.samsantech.souschef.data.Recipe
import com.samsantech.souschef.firebase.FirebaseRecipeManager
import kotlinx.coroutines.flow.MutableStateFlow

class OwnRecipesViewModel(
    private val userViewModel: UserViewModel,
    private val firebaseRecipeManager: FirebaseRecipeManager
) {
    val newRecipe = MutableStateFlow<Recipe>(Recipe())
    val recipes = MutableStateFlow<List<Recipe>>(listOf())

    init {
        firebaseRecipeManager.getOwnRecipes {
            recipes.value = it
        }
    }

    fun uploadRecipe(callback: (Boolean, String?) -> Unit) {
        val user = userViewModel.user.value

        if (user != null) {
            firebaseRecipeManager.addRecipe(
                recipe = newRecipe.value,
                user = user,
                callback = { isSuccess, error ->
                    callback(isSuccess, error)
                },
                uploadedRecipe = { uploadedRecipe ->
                    val updatedRecipes = recipes.value.toMutableList()
                    val recipeIndexToUpdate = updatedRecipes.indexOfFirst { it.id == uploadedRecipe.id }

                    if (recipeIndexToUpdate != -1) {
                        updatedRecipes[recipeIndexToUpdate] = uploadedRecipe
                    } else {
                        updatedRecipes.add(uploadedRecipe)
                    }

                    recipes.value = updatedRecipes
                }
            )
        }
    }

    fun resetNewRecipe() {
        newRecipe.value = Recipe()
    }

    fun addPhoto(key: String, value: Uri) {
        val photos = HashMap<String, Uri>(newRecipe.value.photosUri.toMap())
        photos[key] = value
        newRecipe.value = newRecipe.value.copy(
            photosUri = photos
        )
    }

    fun removePhoto(key: String) {
        val photos = HashMap<String, Uri>(newRecipe.value.photosUri.toMap())
        photos.remove(key)
        newRecipe.value = newRecipe.value.copy(
            photosUri = photos
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

    fun addIngredient() {
        val newIngredients = newRecipe.value.ingredients.toMutableList()
        newIngredients.add("")

        newRecipe.value = newRecipe.value.copy(
            ingredients = newIngredients
        )
    }

    fun updateIngredients(ingredientIndex: Int, value: String) {
        val newIngredients = newRecipe.value.ingredients.mapIndexed { index, ingredient ->
            if(index == ingredientIndex) value else ingredient
        }

        newRecipe.value = newRecipe.value.copy(
            ingredients = newIngredients
        )
    }

    fun removeIngredient(ingredientIndex: Int) {
        val newIngredients = newRecipe.value.ingredients.toMutableList()
        newIngredients.removeAt(ingredientIndex)

        newRecipe.value = newRecipe.value.copy(
            ingredients = newIngredients
        )
    }

    fun setIngredients(ingredients: List<String>) {
        newRecipe.value = newRecipe.value.copy(
            ingredients = ingredients
        )
    }

    fun addInstruction() {
        val newInstructions = newRecipe.value.instructions.toMutableList()
        newInstructions.add("")

        newRecipe.value = newRecipe.value.copy(
            instructions = newInstructions
        )
    }

    fun updateInstructions(instructionIndex: Int, value: String) {
        val newInstructions = newRecipe.value.instructions.mapIndexed { index, instruction ->
            if(index == instructionIndex) value else instruction
        }

        newRecipe.value = newRecipe.value.copy(
            instructions = newInstructions
        )
    }

    fun removeInstruction(instructionIndex: Int) {
        val newInstructions = newRecipe.value.instructions.toMutableList()
        newInstructions.removeAt(instructionIndex)

        newRecipe.value = newRecipe.value.copy(
            instructions = newInstructions
        )
    }

    fun setInstructions(instructions: List<String>) {
        newRecipe.value = newRecipe.value.copy(
            instructions = instructions
        )
    }

    fun addTag(tag: String) {
        newRecipe.value = newRecipe.value.copy(
            tags = newRecipe.value.tags.plus(tag)
        )
    }

    fun removeTag(tag: String) {
        newRecipe.value = newRecipe.value.copy(
            tags = newRecipe.value.tags.minus(tag)
        )
    }
}
package com.culinario.viewmodels

import android.content.Context
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.culinario.mvp.models.repository.recipe.RecipeRepository
import com.culinario.mvp.models.repository.user.UserRepository

class RecipePageViewModel (
    private val recipeId: String,
    val recipeRepository: RecipeRepository,
    val userRepository: UserRepository,
    val context: Context
) {
    fun getRecipe(): Recipe {
        return recipeRepository.getRecipeById(recipeId)
    }

    fun getUserOwner(): User {
        return userRepository.getUserById(getRecipe().userId)
    }
}
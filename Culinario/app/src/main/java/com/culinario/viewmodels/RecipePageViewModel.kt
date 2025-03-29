package com.culinario.viewmodels

import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.culinario.mvp.models.repository.RecipeRepository
import com.culinario.mvp.models.repository.UserRepository

class RecipePageViewModel (
    private val recipeId: String,
    val recipeRepository: RecipeRepository,
    val userRepository: UserRepository
) {
    fun getRecipe(): Recipe {
        return recipeRepository.getRecipeById(recipeId)
    }

    fun getUserOwner(): User {
        return userRepository.getProfile(getRecipe().userId)
    }
}
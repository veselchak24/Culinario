package com.culinario.viewmodels

import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.culinario.mvp.models.repository.RecipeRepository
import com.culinario.mvp.models.repository.UserRepository

class UserPageViewModel (
    private val userId: String,
    val userRepository: UserRepository,
    val recipeRepository: RecipeRepository
) {

    fun getUser(): User {
        return userRepository.getProfile(userId)
    }

    fun getUserRecipes(): List<Recipe> {
        return recipeRepository.getAllRecipes().filter {
            it.userId == userId
        }
    }
}
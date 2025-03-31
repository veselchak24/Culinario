package com.culinario.mvp.views

import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.culinario.mvp.presenters.recipe.RecipeRepository
import com.culinario.mvp.presenters.user.UserRepository

class UserPageViewModel (
    private val userId: String,
    val userRepository: UserRepository,
    val recipeRepository: RecipeRepository
) {
    fun getUser(): User {
        return userRepository.getUserById(userId)
    }

    fun getUserRecipes(): List<Recipe> {
        return recipeRepository.getAllRecipes().filter {
            it.userId == userId
        }
    }

    fun likesCount(): Int {
        return recipeRepository.getAllRecipes().filter {
            it.userId == userId
        }.sumOf {
            it.otherInfo.likes
        }
    }

    fun watchesCount(): Int {
        return recipeRepository.getAllRecipes().filter {
            it.userId == userId
        }.sumOf {
            it.otherInfo.watches
        }
    }

    fun recipeCount(): Int {
        return recipeRepository.getAllRecipes().count {
            it.userId == userId
        }
    }
}
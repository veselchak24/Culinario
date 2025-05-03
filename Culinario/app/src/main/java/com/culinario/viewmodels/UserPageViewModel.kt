package com.culinario.viewmodels

import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.culinario.repository.recipe.RecipeRepository
import com.culinario.repository.user.UserRepository

class UserPageViewModel (
    private val userId: UInt,
) {
    fun getUser(): User {
        return UserRepository.get(userId)
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
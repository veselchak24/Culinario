package com.culinario.mvp.models.repository.recipe

import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeType

/**
*Интерфейс RecipeRepository определяет методы для работы с рецептами.
**/
interface RecipeRepository {
    fun addRecipe(recipe: Recipe)

    fun commit()

    fun getAllRecipes(): List<Recipe>

    fun getRecipeById(id: String): Recipe

    fun searchRecipesByTitle(title: String): List<Recipe>

    fun searchRecipesByIngredients(ingredients: List<String>): List<Recipe>

    fun searchRecipesByCookingSpeed(maxSpeed: Int): List<Recipe>

    fun searchRecipesByType(type: RecipeType): List<Recipe>

    fun searchRecipesByMealTime(mealTime: String): List<Recipe>
}
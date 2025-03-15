package com.culinario.mvp.models

interface RecipeRepository {
    fun getAllRecipes(): List<Recipe>
    fun searchRecipesByTitle(title: String): List<Recipe>
    fun searchRecipesByIngredients(ingredients: List<String>): List<Recipe>
    fun searchRecipesByCookingSpeed(maxSpeed: Int): List<Recipe>
    fun searchRecipesByType(type: RecipeType): List<Recipe>
    fun searchRecipesByMealTime(mealTime: String): List<Recipe>
}
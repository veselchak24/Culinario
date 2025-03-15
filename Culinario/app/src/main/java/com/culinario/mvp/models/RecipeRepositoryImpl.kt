package com.culinario.mvp.models

class RecipeRepositoryImpl : RecipeRepository {
    override fun getAllRecipes(): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }

    override fun searchRecipesByTitle(title: String): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }

    override fun searchRecipesByIngredients(ingredients: List<String>): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }

    override fun searchRecipesByCookingSpeed(maxSpeed: Int): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }

    override fun searchRecipesByType(type: RecipeType): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }

    override fun searchRecipesByMealTime(mealTime: String): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }
}
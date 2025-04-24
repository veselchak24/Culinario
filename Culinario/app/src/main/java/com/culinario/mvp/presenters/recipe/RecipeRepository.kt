<<<<<<<< HEAD:Culinario/app/src/main/java/com/culinario/mvp/presenters/recipe/RecipeRepository.kt
package com.culinario.mvp.presenters.recipe
========
package com.culinario.mvp.repository.recipe
>>>>>>>> 0132463 (idk):Culinario/app/src/main/java/com/culinario/mvp/repository/recipe/RecipeRepository.kt

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
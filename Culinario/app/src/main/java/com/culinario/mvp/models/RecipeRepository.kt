package com.culinario.mvp.models
// Интерфейс RecipeRepository определяет методы для работы с рецептами.
interface RecipeRepository {
    // Получает список всех рецептов.
    fun getAllRecipes(): List<Recipe>

    // Ищет рецепты по заголовку (названию).
    fun searchRecipesByTitle(title: String): List<Recipe>

    // Ищет рецепты по списку ингредиентов.
    fun searchRecipesByIngredients(ingredients: List<String>): List<Recipe>

    // Ищет рецепты по времени приготовления.
    fun searchRecipesByCookingSpeed(maxSpeed: Int): List<Recipe>

    // Ищет рецепты по типу (например, десерт, основное блюдо и т.д.).
    fun searchRecipesByType(type: RecipeType): List<Recipe>

    // Ищет рецепты по времени приема пищи (например, завтрак, обед, ужин).
    fun searchRecipesByMealTime(mealTime: String): List<Recipe>
}
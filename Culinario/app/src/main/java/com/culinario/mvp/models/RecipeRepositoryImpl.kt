package com.culinario.mvp.models
//// Реализация интерфейса RecipeRepository.
//// В данный момент все методы не реализованы и выбрасывают исключение NotImplementedError.
class RecipeRepositoryImpl : RecipeRepository {
    // Возвращает список всех рецептов.
    override fun getAllRecipes(): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }
    // Ищет рецепты по заголовку.
    override fun searchRecipesByTitle(title: String): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }
    // Ищет рецепты по ингредиентам.
    override fun searchRecipesByIngredients(ingredients: List<String>): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }
    // Ищет рецепты по времени приготовления.
    override fun searchRecipesByCookingSpeed(maxSpeed: Int): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }
    // Ищет рецепты по типу.
    override fun searchRecipesByType(type: RecipeType): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }
    // Ищет рецепты по времени приема пищи.
    override fun searchRecipesByMealTime(mealTime: String): List<Recipe> {
        throw NotImplementedError("Not yet implemented")
    }
}
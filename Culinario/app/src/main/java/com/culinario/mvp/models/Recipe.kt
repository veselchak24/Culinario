package com.culinario.mvp.models

data class Recipe(
    val name: String, // Строка, представляющая название рецепта
    val cookingSpeed: Int, // Скорость приготовления в минутах
    val ingredients: List<String>, // Список ингредиентов
    val hasStepByStep: Boolean // Наличие пошагового приготовления
) {
    init {
        require(name.isNotBlank()) { "Recipe name cannot be blank." }
        require(cookingSpeed >= 0) { "Cooking speed must be non-negative." }
        require(ingredients.isNotEmpty()) { "Ingredients list cannot be empty." }
    }
}
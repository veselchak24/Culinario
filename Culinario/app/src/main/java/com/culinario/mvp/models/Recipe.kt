package com.culinario.mvp.models
data class Recipe(
    val name: String, //Строка, представляющая название рецепта
    val cookingSpeed: Int, // Скорость приготовления в минутах
    val ingredients: List<String>, // Список ингредиентов
    val hasStepByStep: Boolean // Наличие пошагового приготовления
)
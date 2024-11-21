package com.culinario.mvp.models.filters

import com.culinario.mvp.models.Recipe

class ProductFilter(private val ingredients: List<String>, private val matchAll: Boolean) : Filter() {
    override fun apply(recipes: List<Recipe>): List<Recipe> {
        // Проверка на пустые ингредиенты
        if (ingredients.isEmpty()) return recipes

        return recipes.filter { recipe ->
            // Проверка на наличие ингредиентов в рецепте
            val recipeIngredients = recipe.ingredients
            if (matchAll) {
                // Фильтрация по всем ингредиентам
                ingredients.all { ingredient -> recipeIngredients.contains(ingredient) }
            } else {
                // Фильтрация по хотя бы одному ингредиенту
                ingredients.any { ingredient -> recipeIngredients.contains(ingredient) }
            }
        }
    }
}
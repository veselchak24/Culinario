package com.culinario.mvp.models.filters

import com.culinario.mvp.models.Recipe

class ProductFilter(private val ingredients: List<String>, private val matchAll: Boolean) : Filter() {
    init {
        require(ingredients.isNotEmpty()) { "Ingredients list cannot be empty." }
        ingredients.forEach { ingredient ->
            require(ingredient.isNotBlank()) { "Ingredient cannot be blank." }
        }
    }

    override fun apply(recipes: List<Recipe>): List<Recipe> {
        require(recipes.isNotEmpty()) { "Recipe list cannot be empty." }

        throw NotImplementedError("")

//        return recipes.filter { recipe ->
//            val recipeIngredients = recipe.ingredients
//            if (matchAll) {
//                ingredients.all { ingredient -> recipeIngredients.contains(ingredient) }
//            } else {
//                ingredients.any { ingredient -> recipeIngredients.contains(ingredient) }
//            }
//        }
    }
}
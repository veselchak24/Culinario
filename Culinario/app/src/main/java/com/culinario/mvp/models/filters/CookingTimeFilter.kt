package com.culinario.mvp.models.filters

import com.culinario.mvp.models.Recipe

class CookingTimeFilter(private val maxCookingSpeed: Int) : Filter() {
    init {
        require(maxCookingSpeed >= 0) { "Max cooking speed must be non-negative." }
    }

    override fun apply(recipes: List<Recipe>): List<Recipe> {
        require(recipes.isNotEmpty()) { "Recipe list cannot be empty." }
        return recipes.filter { it.cookingSpeed <= maxCookingSpeed }
    }
}
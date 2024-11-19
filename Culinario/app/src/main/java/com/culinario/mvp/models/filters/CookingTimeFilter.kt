// CookingTimeFilter позволяет фильтровать список рецептов, оставляя только те,
// которые можно приготовить с заданной максимальной скоростью.
package com.culinario.mvp.models.filters
import com.culinario.mvp.models.Recipe
class CookingTimeFilter(private val maxCookingSpeed: Int) : Filter() {
    override fun apply(recipes: List<Recipe>): List<Recipe> {
        return recipes.filter { it.cookingSpeed <= maxCookingSpeed }
    }
}




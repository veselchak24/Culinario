//Класс ProductFilter позволяет фильтровать список рецептов,
// оставляя только те, которые содержат определенный ингредиент.
package com.culinario.mvp.models.filters
import com.culinario.mvp.models.Recipe
class ProductFilter(private val ingredient: String) : Filter() {
    override fun apply(recipes: List<Recipe>): List<Recipe> {
        return recipes.filter { it.ingredients.contains(ingredient)}
    }
}

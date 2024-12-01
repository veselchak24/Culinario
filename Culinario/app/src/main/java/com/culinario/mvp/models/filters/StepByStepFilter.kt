//класс StepByStepFilter позволяет фильтровать рецепты по наличию
// или отсутствию пошаговых инструкций в зависимости от значений
package com.culinario.mvp.models.filters

import com.culinario.mvp.models.Recipe

class StepByStepFilter(private val required: Boolean) : Filter() {
    override fun apply(recipes: List<Recipe>): List<Recipe> {
        require(recipes.isNotEmpty()) { "Recipe list cannot be empty." }
        return recipes.filter { it.hasStepByStep == required }
    }
}

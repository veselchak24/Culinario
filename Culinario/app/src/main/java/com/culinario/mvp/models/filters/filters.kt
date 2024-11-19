package com.culinario.mvp.models.filters
import com.culinario.mvp.models.Recipe
abstract class Filter {
    abstract fun apply(recipes: List<Recipe>): List<Recipe>
}
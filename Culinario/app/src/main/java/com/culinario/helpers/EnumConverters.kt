package com.culinario.helpers

import androidx.compose.ui.graphics.Color
import com.culinario.mvp.models.Difficulty
import com.culinario.mvp.models.RecipeType

fun Difficulty.asWord(): String =
    when (this) {
        Difficulty.EASY -> "лёгкий"
        Difficulty.MEDIUM -> "средний"
        Difficulty.HARD -> "сложный"
    }

fun RecipeType.asWord(): String = when (this) {
    RecipeType.QUICK -> "быстрый"
    RecipeType.COMPLEX -> "сложный"
    RecipeType.VEGAN -> "веганский"
    RecipeType.GLUTEN_FREE -> "без глютена"
    RecipeType.BAKING -> "выпечка"
}

fun RecipeType.asColor(): Color = when(this) {
    RecipeType.QUICK -> Color(0xFF4285F4) // Синий
    RecipeType.COMPLEX -> Color(0xFFEA4335) // Красный
    RecipeType.VEGAN -> Color(0xFF0F9D58) // Зеленый
    RecipeType.GLUTEN_FREE -> Color(0xFFFBBC05) // Желтый
    RecipeType.BAKING -> Color(0xFF9C27B0) // Фиолетовый
}
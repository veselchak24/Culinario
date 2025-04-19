package com.culinario.mvp.models

import androidx.annotation.Size
import kotlinx.serialization.Serializable

/**
 * Класс RecipeCategory представляет категорию рецепта с уникальным идентификатором и названием.
 * @param Id Уникальный идентификатор категории рецепта.
 * @param Name Название категории рецепта, длина строки не должна превышать 20 символов.
 */
@Serializable
data class RecipeCategory(
    override val Id: Int,
    @Size(max = 20) val Name: String,
) : IModel




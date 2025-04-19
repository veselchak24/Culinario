package com.culinario.mvp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Класс RecipeSteps представляет шаги приготовления рецепта с уникальным идентификатором, идентификатором рецепта, описанием и изображениями.
 * @param Id Уникальный идентификатор шага приготовления рецепта.
 * @param Recipe Идентификатор рецепта, к которому относится шаг приготовления.
 * @param Description Описание шага приготовления рецепта.
 * @param Images Список изображений, иллюстрирующих шаг приготовления рецепта.
 */
@Serializable
data class RecipeSteps(
    override val Id: Int,
    @SerialName("RecipeId") val Recipe: Recipe,
    var Description: String,
    val Images: List<Image>?,
) : IModel
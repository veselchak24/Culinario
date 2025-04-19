package com.culinario.mvp.models

import kotlinx.serialization.Serializable

/**
 * Класс RecipeImage представляет изображение рецепта с уникальным идентификатором, данными изображения и идентификатором рецепта.
 * @param Id Уникальный идентификатор изображения.
 * @param Data Данные изображения в виде массива байтов.
 * @param RecipeId Идентификатор рецепта, к которому относится изображение.
 */
@Serializable
data class RecipeImage(
    override val Id: Int,
    override val Data: ByteArray,
    val RecipeId: Recipe,
) : Image
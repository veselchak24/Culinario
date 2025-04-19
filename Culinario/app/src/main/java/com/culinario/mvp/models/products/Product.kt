package com.culinario.mvp.models

import androidx.annotation.Size
import kotlinx.serialization.Serializable

/**
 * Класс Product представляет продукт с уникальным идентификатором, названием, описанием, информацией о питательных веществах и идентификатором категории продукта.
 * @param Id Уникальный идентификатор продукта.
 * @param Name Название продукта.
 * @param Description Описание продукта.
 * @param Nutritional Информация о питательных веществах продукта.
 * @param Category Информация о категории продукта.
 */
@Serializable
class Product(
    override val Id: Int,
    @Size(max = 100) val Name: String,
    @Size(max = 1000) val Description: String,
    val Nutritional: Nutritional,
    val Category: ProductCategory,
) : IModel
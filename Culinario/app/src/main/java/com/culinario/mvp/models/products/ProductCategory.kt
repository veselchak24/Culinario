package com.culinario.mvp.models

import androidx.annotation.Size
import kotlinx.serialization.Serializable

/**
 * Класс ProductCategory представляет категорию продукта с уникальным идентификатором, названием и изображением.
 * @param Id Уникальный идентификатор категории продукта.
 * @param Name Название категории продукта, длина строки не должна превышать 50 символов.
 * @param Image Изображение категории продукта в виде массива байтов.
 */
@Serializable
class ProductCategory(
    override val Id: Int,
    @Size(max = 50) val Name: String,
    val Image: ByteArray,
) : IModel
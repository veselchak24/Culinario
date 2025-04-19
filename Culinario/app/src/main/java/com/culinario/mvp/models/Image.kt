package com.culinario.mvp.models

/**
 * Интерфейс Image
 * @property Data данные изображения в виде массива байтов.
 */

data class Image(
    override val Id: Int,
    val Data: ByteArray,
) : IModel
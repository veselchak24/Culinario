package com.culinario.mvp.models

import kotlinx.serialization.Serializable

/**
 * Интерфейс Image
 * @property Data данные изображения в виде массива байтов.
 */

@Serializable
data class Image(
    override val Id: Int,
    val Data: ByteArray,
) : IModel
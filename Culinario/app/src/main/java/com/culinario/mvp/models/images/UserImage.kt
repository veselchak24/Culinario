package com.culinario.mvp.models

import kotlinx.serialization.Serializable

/**
 * Класс UserImage представляет изображение пользователя с уникальным идентификатором, данными изображения и идентификатором пользователя.
 * @param Id Уникальный идентификатор изображения.
 * @param Data Данные изображения в виде массива байтов.
 * @param UserId Идентификатор пользователя, к которому относится изображение.
 */
@Serializable
class UserImage(
    override val Id: Int,
    override val Data: ByteArray,
    val UserId: User,
) : Image


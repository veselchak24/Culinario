package com.culinario.mvp.models

import androidx.annotation.Size
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Класс Comment представляет комментарий с уникальным идентификатором, текстом комментария, идентификатором рецепта, идентификатором пользователя, временем создания, количеством лайков, дизлайков и идентификатором комментария, на который был дан ответ.
 * @param Id Уникальный идентификатор комментария.
 * @param Text Текст комментария, длина строки не должна превышать 5000 символов.
 * @param Recipe Идентификатор рецепта, к которому относится комментарий.
 * @param User Идентификатор пользователя, оставившего комментарий.
 * @param TimeCreated Время создания комментария.
 * @param Likes Количество лайков комментария.
 * @param Dislikes Количество дизлайков комментария.
 * @param ReplyComment Идентификатор комментария, на который был дан ответ.
 */
@Serializable
data class Comment(
    override val Id: Int,
    @Size(max = 5000) var Text: String,
    val Recipe: Recipe,
    val User: User,
    var TimeCreated: String,
    var Likes: UInt,
    var Dislikes: UInt,
    val ReplyComment: Comment?,
) : IModel
package com.culinario.mvp.models

import androidx.annotation.Size
import kotlinx.serialization.Serializable

/**
 * Класс Recipe представляет рецепт с уникальным идентификатором, названием, описанием, категорией, изображением, идентификатором пользователя, списком продуктов, шагами приготовления, количеством лайков, дизлайков, просмотров и комментариев.
 * @param Id Уникальный идентификатор рецепта.
 * @param Name Название рецепта, длина строки не должна превышать 100 символов.
 * @param Description Описание рецепта, длина строки не должна превышать 500 символов.
 * @param Category Категория рецепта.
 * @param Image Изображение рецепта.
 * @param UserId Идентификатор пользователя, создавшего рецепт.
 * @param Products Список продуктов, используемых в рецепте.
 * @param Steps Список шагов приготовления рецепта.
 * @param Likes Количество лайков рецепта.
 * @param Dislikes Количество дизлайков рецепта.
 * @param Views Количество просмотров рецепта.
 * @param Comments Список комментариев к рецепту.
 */
@Serializable
class Recipe(
    override val Id: Int,
    @Size(max = 100) var Name: String,
    @Size(max = 500) var Description: String,
    var Category: RecipeCategory,
    var Image: RecipeImage?,
    var UserId: User,
    var Products: List<Product>,
    var Steps: List<RecipeSteps>?,
    var Likes: UInt,
    var Dislikes: UInt,
    var Views: UInt,
    var Comments: List<Comment>?,
) : IModel
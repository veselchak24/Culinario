package com.culinario.mvp.models

import androidx.annotation.Size
import kotlinx.serialization.Serializable

/**
 * Класс User представляет пользователя с уникальным идентификатором, именем, электронной почтой, изображением пользователя, списком рецептов и списком избранных рецептов.
 * @param Id Уникальный идентификатор пользователя.
 * @param Name Имя пользователя, длина строки не должна превышать 40 символов.
 * @param Email Электронная почта пользователя, длина строки не должна превышать 320 символов.
 * @param UserImage Изображение пользователя.
 * @param Recipes Список рецептов, созданных пользователем.
 * @param FavoriteRecipes Список избранных рецептов пользователя.
 */
@Serializable
data class User(
    override val Id: Int,
    @Size(max = 40) var Name: String,
    @Size(max = 320) var Email: String? = null,
    var UserImage: Image? = null,
    var Recipes: List<Recipe>? = null,
    var FavoriteRecipes: List<Recipe>? = null,
) : IModel
package com.culinario.mvp.models

import com.culinario.backend.interfaces.IOnDeserialize
import com.culinario.backend.interfaces.IOnSerialize

/**
 * Модель данных рецепта.
 *
 * @param[name] название рецепта.
 * @param[description] описание рецепта.
 * @param[imageUrl] ссылка на изображение.
 * @param[author] автор рецепта.
 * @param[ingredients] список ингредиентов.
 * @param[steps] step-by-step приготовление.
 * @param[recipeType] тип рецепта.
 * @param[otherCharacteristics] другие свойства рецепта.
 */
class Recipe (
    val name: String,
    val description: String,
    val imageUrl: String,
    val author: Author,
    val ingredients: List<Ingredient>,
    val cookingSpeed: Int, // Скорость приготовления в минутах
    val steps: List<String>,
    val recipeType: RecipeType,
    var otherCharacteristics: Map<String, Any> = emptyMap()
) : IOnSerialize, IOnDeserialize {

    override fun onSerialize() {
        //TODO("Not yet implemented")
    }

    override fun onDeserialize() {
        //TODO("Not yet implemented")
    }
}

/**
 * Автор рецпта (я так понимаю, мы его скоро заменим на класс User)
 */
data class Author(val name: String, val email: String? = null)

/**
 * Абстрактный класс ингредиента.
 *
 * @property name Название продукта.
 * @property quantity количество продукта.
 * @property unit единица измерения.
 */
data class Ingredient (
    val name: String,
    val quantity: Double?,
    val unit: Unit?
)

/**
 * Единица измерения ингредиента
 */
enum class Unit {
    /** Кол-во в шт. */
    PIECE,

    /** Кол-во в граммах. */
    GRAMS,

    /** Кол-во в литрах. */
    LITERS,

    /** Кол-во в чайных ложках. */
    TEASPOONS,

    /** Кол-во в столовых ложках. */
    TABLESPOONS,

    /** Кол-во в чашках. */
    CUPS
}

/**
 * Тип рецепта
 */
enum class RecipeType {
    QUICK, COMPLEX, VEGAN, GLUTEN_FREE, BAKING
}

/**
 * Уровень сложности рецепта
 */
enum class Difficulty {
    EASY, MEDIUM, HARD
}
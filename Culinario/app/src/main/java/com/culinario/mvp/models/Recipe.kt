package com.culinario.mvp.models

import android.net.Uri
import com.culinario.backend.interfaces.IOnDeserialize
import com.culinario.backend.interfaces.IOnSerialize

/**
 * Модель данных рецепта.
 *
 * @param[id] идентификатор рецепта
 * @param[userId] id владельца рецепта.
 * @param[name] название рецепта.
 * @param[description] описание рецепта.
 * @param[recipeImageResources] Ресурсы изображений для рецептов.
 * @param[ingredients] список ингредиентов.
 * @param[cookingSpeed] скорость приготовления (в минутах)
 * @param[steps] step-by-step приготовление.
 * @param[recipeType] тип рецепта.
 * @param[difficulty] сложность рецепта.
 * @param[otherCharacteristics] другие свойства рецепта.
 * @param[otherInfo] дополнительная информация.
 */
class Recipe (
    val id: String,
    val userId: String,
    val name: String,
    val description: String,
    val recipeImageResources: RecipeImageResources,
    val ingredients: List<Ingredient>,
    val cookingSpeed: Int,
    val steps: List<String>,
    val recipeType: RecipeType,
    val difficulty: Difficulty,
    var otherCharacteristics: Map<String, Any> = emptyMap(),
    var otherInfo: OtherInfo
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

/**
 *@param[recipePicturesResources] временное решение для плейсхолдера
 *@param[recipeBackgroundImageResources] временное решение для плейсхолдера
 */
data class RecipeImageResources (
    var recipeBackgroundImageUri: Uri? = null,
    var recipePicturesUri: Array<Uri>? = null,

    var recipeBackgroundImageResources: Int? = null,
    var recipePicturesResources: Array<Int>? = null
)

data class OtherInfo(val watches: Int, val likes: Int)
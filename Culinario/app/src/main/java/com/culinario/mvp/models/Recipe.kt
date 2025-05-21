package com.culinario.mvp.models

import com.culinario.backend.interfaces.IOnDeserialize
import com.culinario.backend.interfaces.IOnSerialize
import kotlinx.serialization.Serializable

/**
 * Модель данных рецепта.
 *
 * @param[id] идентификатор рецепта
 * @param[userId] id владельца рецепта.
 * @param[name] название рецепта.
 * @param[description] описание рецепта.
 * @param[recipeImageBackgroundUrl] Ссылка на картинку заднего фона рецепта.
 * @param[recipeImagesUrl] Ссылка на картинки рецепта.
 * @param[ingredients] список ингредиентов.
 * @param[cookingSpeed] скорость приготовления (в минутах)
 * @param[steps] step-by-step приготовление.
 * @param[recipeType] тип рецепта.
 * @param[difficulty] сложность рецепта.
 * @param[otherInfo] дополнительная информация.
 * @param[nutritionInfo] информация о КБЖУ рецепта.
 */
@Serializable
class Recipe (
    val id: String = "",
    var userId: String = "",
    val name: String = "",
    val description: String = "",
    val recipeImageBackgroundUrl: String = "",
    val recipeImagesUrl: List<String> = listOf(),
    val ingredients: List<Ingredient> = listOf(),
    val cookingSpeed: Int = 0,
    val steps: List<String> = listOf(),
    val recipeType: RecipeType = RecipeType.QUICK,
    val difficulty: Difficulty = Difficulty.EASY,
    var otherInfo: OtherInfo = OtherInfo(0, 0),
    val nutritionInfo: NutritionInfo = NutritionInfo()
) : IOnSerialize, IOnDeserialize {

    override fun onSerialize() {
        //TODO("Not yet implemented")
    }

    override fun onDeserialize() {
        //TODO("Not yet implemented")
    }
}

/**
 * Информация о пищевой ценности (КБЖУ)
 *
 * @param[calories] калории (ккал)
 * @param[proteins] белки (г)
 * @param[fats] жиры (г)
 * @param[carbohydrates] углеводы (г)
 */
@Serializable
data class NutritionInfo(
    val calories: Double = 0.0,
    val proteins: Double = 0.0,
    val fats: Double = 0.0,
    val carbohydrates: Double = 0.0
)

/**
 * Абстрактный класс ингредиента.
 *
 * @param[name] Название продукта.
 * @param[guantify] количество продукта.
 * @param[unit] единица измерения.
 */
@Serializable
data class Ingredient (
    val name: String = "default ingredient",
    val quantity: Double? = 0.0,
    val unit: Unit? = Unit.PIECE
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

@Serializable
data class OtherInfo(
    val watches: Int = 0,
    val likes: Int = 0
)
package com.culinario.mvp.models

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
    val recipeImagesUrl: List<String> = listOf(),
    val ingredients: List<Ingredient> = listOf(),
    val commentaries: List<String> = listOf(),
    val cookingSpeed: Int = 0,
    val totalWeight: Int = 0,
    val recipeDescription: RecipeDescription = RecipeDescription(),
    val steps: List<DetailedCookingStep> = listOf(),
    val recipeType: RecipeType = RecipeType.QUICK,
    val difficulty: Difficulty = Difficulty.EASY,
    var otherInfo: OtherInfo = OtherInfo(0, 0),
    val nutritionInfo: NutritionInfo = NutritionInfo()
)

/**
 * Детализированное описание рецепта
 * @param[shortSummary] краткое описание (1-2 предложения)
 * @param[detailedDescription] полное описание с деталями
 * @param[originStory] история происхождения рецепта (опционально)
 * @param[cuisineType] тип кухни (итальянская, азиатская и т.д.)
 * @param[mealType] тип приема пищи (завтрак, обед и т.д.)
 */
@Serializable
data class RecipeDescription(
    val shortSummary: String = "",
    val detailedDescription: String = "",
    val originStory: String = "",
    val cuisineType: String = "",
    val mealType: String = "",
    val tips: List<String> = listOf()
)

@Serializable
data class DetailedCookingStep(
    val imageUrl: String,
    val description: String
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
    val name: String = "",
    val imageUrl: String = "",
    val quantity: Double? = 0.0,
    val unit: String = "",
    val nutritionInfo: NutritionInfo = NutritionInfo(),
    @field:JvmField
    val isOptional: Boolean = true,
    val substitutes: List<Ingredient> = listOf()
)

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

data class Commentary(
    val id: String = "",
    val userId: String = "",
    val timeStamp: Long = 0,
    val text: String = "",
    val likes: Int = 0
)

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
    var watches: Int = 0,
    var likes: Int = 0
)
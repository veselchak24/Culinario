package com.culinario.mvp.models.repository

import com.culinario.mvp.models.Difficulty
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeType
import com.culinario.mvp.models.Unit

class RecipeRepositoryImpl : RecipeRepository {
    private val recipes = listOf(
        Recipe(
            id = "11111111",
            userId = "WaAWgH3212",
            name = "Паста с томатным соусом",
            description = "Вкусная паста с домашним томатным соусом.",
            imageUrl = "url_to_image",
            ingredients = listOf(
                Ingredient(name = "Паста", quantity = 200.0, unit = Unit.GRAMS),
                Ingredient(name = "Томаты", quantity = 300.0, unit = Unit.GRAMS),
                Ingredient(name = "Чеснок", quantity = 2.0, unit = Unit.PIECE)
            ),
            cookingSpeed = 30,
            steps = listOf("Сварить пасту", "Приготовить соус", "Смешать пасту с соусом"),
            recipeType = RecipeType.QUICK,
            difficulty = Difficulty.EASY
        ),
        Recipe(
            id = "11111112",
            userId = "24DR1EFAwd",
            name = "Шоколадный торт",
            description = "Нежный шоколадный торт с кремом.",
            imageUrl = "url_to_image",
            ingredients = listOf(
                Ingredient(name = "Мука", quantity = 250.0, unit = Unit.GRAMS),
                Ingredient(name = "Шоколад", quantity = 100.0, unit = Unit.GRAMS),
                Ingredient(name = "Яйца", quantity = 3.0, unit = Unit.PIECE)
            ),
            cookingSpeed = 60,
            steps = listOf("Смешать ингредиенты", "Выпекать в духовке", "Остудить и подать"),
            recipeType = RecipeType.BAKING,
            difficulty = Difficulty.MEDIUM
        )
    )

    override fun getAllRecipes(): List<Recipe> {
        // Возвращает весь список рецептов, как будто получаем их из базы данных
        return recipes
    }

    override fun getRecipeById(id: String): Recipe {
        return recipes.first { recipe ->
            recipe.id == id
        }
    }

    override fun searchRecipesByTitle(title: String): List<Recipe> {
        // Ищет рецепты по заголовку, как будто выполняем запрос к базе данных
        return recipes.filter { it.name.contains(title, ignoreCase = true) }
    }

    override fun searchRecipesByIngredients(ingredients: List<String>): List<Recipe> {
        // Ищет рецепты по списку ингредиентов
        return recipes.filter { recipe ->
            ingredients.all { ingredient ->
                recipe.ingredients.any { it.name.equals(ingredient, ignoreCase = true) }
            }
        }
    }

    override fun searchRecipesByCookingSpeed(maxSpeed: Int): List<Recipe> {
        // Ищет рецепты по времени приготовления
        return recipes.filter { it.cookingSpeed <= maxSpeed }
    }

    override fun searchRecipesByType(type: RecipeType): List<Recipe> {
        // Ищет рецепты по типу
        return recipes.filter { it.recipeType == type }
    }

    override fun searchRecipesByMealTime(mealTime: String): List<Recipe> {
        // Ищет рецепты по времени приема пищи
        return recipes.filter { recipe ->
            recipe.otherCharacteristics["mealTime"]?.toString()?.equals(mealTime, ignoreCase = true) == true
        }
    }
}
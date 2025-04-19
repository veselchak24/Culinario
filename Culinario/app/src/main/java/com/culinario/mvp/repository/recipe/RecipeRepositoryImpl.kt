package com.culinario.mvp.repository.recipe

import com.culinario.R
import com.culinario.mvp.models.Difficulty
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.OtherInfo
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeImageResources
import com.culinario.mvp.models.RecipeType
import com.culinario.mvp.models.Unit

class RecipeRepositoryImpl : RecipeRepository {
    private val recipes = mutableListOf (
        Recipe (
            id = "11111111",
            userId = "WaAWgH3212",
            name = "Паста с томатным соусом",
            description = "Вкусная паста с домашним томатным соусом.",
            recipeImageResources = RecipeImageResources(
                recipeBackgroundImageResources = R.drawable.recipe_page_bg,
                recipePicturesResources = arrayOf(
                    R.drawable.pasta,
                    R.drawable.pasta2,
                    R.drawable.pasta3
                )
            ),
            ingredients = listOf (
                Ingredient(name = "Паста", quantity = 200.0, unit = Unit.GRAMS),
                Ingredient(name = "Томаты", quantity = 300.0, unit = Unit.GRAMS),
                Ingredient(name = "Чеснок", quantity = 2.0, unit = Unit.PIECE)
            ),
            cookingSpeed = 30,
            steps = listOf("Сварить пасту", "Приготовить соус", "Смешать пасту с соусом"),
            recipeType = RecipeType.QUICK,
            difficulty = Difficulty.EASY,
            otherInfo = OtherInfo(106, 2)
        ),
        Recipe(
            id = "11111112",
            userId = "WaAWgH3212",
            name = "Шоколадный торт",
            description = "Нежный шоколадный торт с кремом.",
            recipeImageResources = RecipeImageResources (
                recipeBackgroundImageResources = R.drawable.chocolate_cake_2,
                recipePicturesResources = arrayOf (
                    R.drawable.chocolate_cake,
                    R.drawable.chocolate_cake_2,
                    R.drawable.chocolate_cake_3
                )
            ),
            ingredients = listOf(
                Ingredient(name = "Мука", quantity = 250.0, unit = Unit.GRAMS),
                Ingredient(name = "Шоколад", quantity = 100.0, unit = Unit.GRAMS),
                Ingredient(name = "Яйца", quantity = 3.0, unit = Unit.PIECE)
            ),
            cookingSpeed = 60,
            steps = listOf("Смешать ингредиенты", "Выпекать в духовке", "Остудить и подать"),
            recipeType = RecipeType.BAKING,
            difficulty = Difficulty.MEDIUM,
            otherInfo = OtherInfo(100, 5)
        )
    )

    override fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    override fun commit() {

    }

    override fun getAllRecipes(): List<Recipe> {
        return recipes
    }

    override fun getRecipeById(id: String): Recipe {
        return recipes.first { recipe ->
            recipe.id == id
        }
    }

    override fun searchRecipesByTitle(title: String): List<Recipe> {
        return recipes.filter { it.name.contains(title, ignoreCase = true) }
    }

    override fun searchRecipesByIngredients(ingredients: List<String>): List<Recipe> {
        return recipes.filter { recipe ->
            ingredients.all { ingredient ->
                recipe.ingredients.any { it.name.equals(ingredient, ignoreCase = true) }
            }
        }
    }

    override fun searchRecipesByCookingSpeed(maxSpeed: Int): List<Recipe> {
        return recipes.filter { it.cookingSpeed <= maxSpeed }
    }

    override fun searchRecipesByType(type: RecipeType): List<Recipe> {
        return recipes.filter { it.recipeType == type }
    }

    override fun searchRecipesByMealTime(mealTime: String): List<Recipe> {
        throw NotImplementedError()
//        return recipes.filter { recipe ->
//            recipe.otherCharacteristics["mealTime"]?.toString()?.equals(mealTime, ignoreCase = true) == true
//        }
    }
}
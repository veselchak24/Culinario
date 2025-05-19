package com.culinario.mvp.models.repository.recipe

import android.content.Context
import com.culinario.backend.RECIPE_JSON_FILE_NAME
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeType
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.reflect.Type


class LocalSaveRecipeRepository (
    val context: Context
) : RecipeRepository {
    private val file = File(context.applicationContext.filesDir, RECIPE_JSON_FILE_NAME)
    private var recipes = mutableListOf<Recipe>()

    init {
        val listType: Type = TypeToken.getParameterized(MutableList::class.java, Recipe::class.java).type

        recipes = Gson().fromJson(file.readText(), listType)
    }

    override fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    override fun commit() {
        file.writeText(GsonBuilder().setPrettyPrinting().create().toJson(recipes))
    }

    override fun getAllRecipes(): List<Recipe> {
        return recipes
    }

    override fun getRecipeById(id: String): Recipe {
        return recipes.first {
            it.id == id
        }
    }

    override fun searchRecipesByTitle(title: String): List<Recipe> {
        TODO("Not yet implemented")
    }

    override fun searchRecipesByIngredients(ingredients: List<String>): List<Recipe> {
        TODO("Not yet implemented")
    }

    override fun searchRecipesByCookingSpeed(maxSpeed: Int): List<Recipe> {
        TODO("Not yet implemented")
    }

    override fun searchRecipesByType(type: RecipeType): List<Recipe> {
        TODO("Not yet implemented")
    }

    override fun searchRecipesByMealTime(mealTime: String): List<Recipe> {
        TODO("Not yet implemented")
    }
}
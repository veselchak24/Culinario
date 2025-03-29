package com.culinario.backend

import android.content.Context
import com.culinario.mvp.models.Recipe

object LocalRecipesHandler {
    private var recipes : MutableList<Recipe> = mutableListOf()
    private var localSaverLoader: LocalRecipeSaverLoader = LocalRecipeSaverLoader()

    public fun UpdateLocalRecipes(context: Context) {
        recipes = (localSaverLoader.load(RECIPE_JSON_FILE_NAME, context) as Array<Recipe>).toMutableList()
    }

    public fun SaveLocalRecipes(context: Context) {
        localSaverLoader.save(recipes, context)
    }

    public fun AddRecipe(newRecipe: Recipe, context: Context) {
        recipes.add(newRecipe)

        SaveLocalRecipes(context)
    }

    public fun AddArrayOfRecipe(newRecipes: Array<Recipe>, context: Context) {
        recipes.addAll(newRecipes)

        SaveLocalRecipes(context)
    }

    public fun GetLocalRecipes(context: Context): List<Recipe> {
        UpdateLocalRecipes(context)

        return recipes
    }

    public fun GetLocalRecipesCount(): Int = recipes.count()
}
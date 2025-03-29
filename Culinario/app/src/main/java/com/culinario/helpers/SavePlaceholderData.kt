package com.culinario.helpers

import android.content.Context
import com.culinario.backend.PROFILE_JSON_FILE_NAME
import com.culinario.backend.RECIPE_JSON_FILE_NAME
import com.culinario.mvp.models.repository.recipe.RecipeRepository
import com.culinario.mvp.models.repository.user.UserRepository
import com.google.gson.GsonBuilder
import java.io.File

class SavePlaceholderData (
    private val userRepository: UserRepository,
    private val recipeRepository: RecipeRepository,
    val context: Context
) {
    fun saveIfFilesNotExists() {
        val recipesFile = File(context.applicationContext.filesDir, RECIPE_JSON_FILE_NAME)
        val userFile = File(context.applicationContext.filesDir, PROFILE_JSON_FILE_NAME)

        if (!recipesFile.exists()) {
            recipesFile.createNewFile()
            recipesFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(recipeRepository.getAllRecipes()))
        }

        if (!userFile.exists()) {
            userFile.createNewFile()
            userFile.writeText(GsonBuilder().setPrettyPrinting().create().toJson(userRepository.getAllUsers()))
        }
    }
}
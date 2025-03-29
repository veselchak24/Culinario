package com.culinario.backend

import android.content.Context
import com.culinario.backend.interfaces.ILoader
import com.culinario.backend.interfaces.ISaver
import com.culinario.mvp.models.Recipe
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File

/**
 *Класс локального сохранения рецепта
 */
class LocalRecipeSaverLoader(private val fileName: String = RECIPE_JSON_FILE_NAME) : ISaver, ILoader {

    override fun save(dataToSave: Any, context: Context): String {
        val gson = GsonBuilder().setPrettyPrinting().create();

        val jsonData = gson.toJson(dataToSave)
        println(jsonData)

        saveDataToFile(jsonData, fileName, context)

        return jsonData
    }

    private fun saveDataToFile(stringData: String, fileName: String, context: Context) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(stringData.toByteArray())
        }
    }


    override fun load(fileName: String, context: Context): Any {
        return loadDataFromFile(fileName, context)
    }

    private fun loadDataFromFile(fileName: String, context: Context): Array<Recipe> {
        var jsonStr = ""

        val file = File(context.applicationContext.filesDir, RECIPE_JSON_FILE_NAME)

        if (!file.exists()) {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { it.write("[ ]".toByteArray()) }
        }

        context.openFileInput(fileName).use {
            jsonStr = String(it.readBytes())
        }

        return Gson().fromJson(jsonStr, arrayOf<Recipe>()::class.java)
    }
}
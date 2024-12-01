package com.culinario.backend

import android.content.Context
import com.culinario.backend.interfaces.ILoader
import com.culinario.backend.interfaces.ISaver
import com.culinario.mvp.models.Recipe
import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 *Класс локального сохранения рецепта
 */
class LocalRecipeSaverLoader : ISaver, ILoader {
    //TODO: Временный хардкод имени файла, уберу в будущем
    val fileName = "test.json"

    override fun save(dataToSave: Any, context: Context): String {
        val gson = GsonBuilder().setPrettyPrinting().create();

        val jsonData = gson.toJson(dataToSave)
        println(jsonData)

        saveDataToFile(jsonData, fileName, context)

        return jsonData
    }

    override fun load(path: String, context: Context): Any {
        return loadDataFromFile(fileName, context)
    }

    private fun loadDataFromFile(fileName: String, context: Context): Recipe {
        var jsonStr = ""
        context.openFileInput(fileName).use {
            jsonStr = String(it.readBytes());
        }

        return Gson().fromJson(jsonStr, Recipe::class.java)
    }

    private fun saveDataToFile(stringData: String, fileName: String, context: Context) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(stringData.toByteArray())
        }
    }
}
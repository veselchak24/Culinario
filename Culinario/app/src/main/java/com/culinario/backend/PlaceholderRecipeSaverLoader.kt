package com.culinario.backend

import android.content.Context
import com.culinario.backend.interfaces.ILoader
import com.culinario.backend.interfaces.ISaver
import com.culinario.mvp.models.Recipe
import com.google.gson.Gson
import java.io.File

class PlaceholderRecipeSaverLoader : ISaver, ILoader  {
    override fun save(dataToSave: Any, context: Context): String = "saved"
    private fun saveDataToFile(stringData: String, fileName: String, context: Context) { }

    override fun load(fileName: String, context: Context): Any {
        return loadDataFromFile(fileName, context)
    }

    private fun loadDataFromFile(fileName: String, context: Context): Array<Recipe> {
        var jsonStr = ""

        val file = File(context.applicationContext.filesDir, RECIPE_JSON_FILE_NAME)

        println("file ${context.applicationContext.filesDir}/$RECIPE_JSON_FILE_NAME exists: ${file.exists()}")

        if (!file.exists()) {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { it.write("[ ]".toByteArray()) }
        }

        context.openFileInput(fileName).use {
            jsonStr = String(it.readBytes())
        }

        return Gson().fromJson(jsonStr, arrayOf<Recipe>()::class.java)
    }
}
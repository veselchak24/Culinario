package com.culinario.backend

import android.content.Context
import com.culinario.backend.interfaces.ILoader
import com.culinario.backend.interfaces.ISaver
import com.culinario.mvp.models.Recipe

class RecipeSaverLoader {
    /**
     * Сохранение рецепта.
     *
     * @param [recipe] Абстрактный класс рецепта, передаваемый для сохранения в Json формате.
     * @param [saver] Объект, реализующий интерфейс [ISaver]. Используется в качестве объекта,
     * исполняющего сохранение.
     * @param [context] Используется для передачи контекста, который позволяет взаимодействовать
     * с системой.
     **/
    fun saveRecipes(recipe: Recipe, saver: ISaver, context: Context) {
        saver.save(recipe, context)
        recipe.onSerialize()
    }

    /**
     * Выгрузка рецепта в объект.
     *
     * @param [filePath] имя (путь до) файла .
     * @param [loader] объект, исполняющий выгрузку Json строки в объект.
     * @param [context] Используется для передачи контекста, который позволяет взаимодействовать с системой.
     */
    fun loadRecipes(filePath: String, loader: ILoader, context: Context): Recipe {
        val recipe: Recipe = loader.load(filePath, context) as Recipe
        recipe.onDeserialize()

        return recipe
    }
}
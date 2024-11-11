package com.culinario.backend

import com.culinario.mvp.models.Recipe

class RecipeSaver {
    ///какой нибудь код тут будет в будущем. я верю в то, что я не зря писал отдельный интерфейс)

    fun SaveRecipe(recipe: Recipe, saver: ISaver) {
        saver.save(recipe)
        recipe.onSerialize()

        println(recipe.toString())
    }
}
package com.culinario.viewmodel

import androidx.lifecycle.ViewModel
import com.culinario.helpers.RECIPE_COLLECTION
import com.culinario.mvp.models.Recipe
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class HomePageViewModel : ViewModel() {
    private val recipeCollection = Firebase.firestore.collection(RECIPE_COLLECTION)

    suspend fun getAllRecipes(): List<Recipe> {
        val recipes = mutableListOf<Recipe>()

        val docs = recipeCollection.get().await()
        for(document in docs.documents) {
            recipes.add(document.toObject<Recipe>()!!)
        }

        return recipes
    }
}
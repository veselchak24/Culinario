package com.culinario.viewmodel

import androidx.lifecycle.ViewModel
import com.culinario.helpers.RECIPE_COLLECTION
import com.culinario.mvp.models.Recipe
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class HomePageViewModel : ViewModel() {
    private val recipeCollection = Firebase.firestore.collection(RECIPE_COLLECTION)

    private val recipesIdState = MutableStateFlow(listOf<String>())
    val recipesId = recipesIdState.asStateFlow()

    init {
        collectRecipeCollection()
    }

    private fun collectRecipeCollection() {
        recipeCollection.addSnapshotListener { recipes, error ->
            if (error == null && recipes != null) {
                recipesIdState.value = recipes.map { it.id }
            } else {
                println(error)
            }
        }
    }
}
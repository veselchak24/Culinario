package com.culinario.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.culinario.helpers.RECIPE_COLLECTION
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RecipePageViewModel(
    private val recipeId: String
) : ViewModel() {
    private val recipeCollection = Firebase.firestore.collection(RECIPE_COLLECTION)
    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)

    var recipeState = MutableStateFlow(Recipe())
    var userState = MutableStateFlow(User())

    init {
        if (recipeState.value.id != recipeId) {
            viewModelScope.launch {
                recipeState.value = recipeCollection.document(recipeId).get().await().toObject<Recipe>()!!

                userState.value = userCollection.document(recipeState.value.userId).get().await().toObject<User>()!!
            }
        }
    }

    private fun exceptionHandler(exc: Exception) {
        Log.e("exception", exc.message!!)
    }
}
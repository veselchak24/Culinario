package com.culinario.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.culinario.helpers.RECIPE_COLLECTION
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RecipeCardViewModel(
    private val recipeId: String,
    private val context: Context
) : ViewModel() {
    private val recipeCollection = Firebase.firestore.collection(RECIPE_COLLECTION)
    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)

    private var recipeState: MutableStateFlow<Recipe> = MutableStateFlow(Recipe())
    var recipe = recipeState.asStateFlow()

    private var userState: MutableStateFlow<User> = MutableStateFlow(User())
    var user = userState.asStateFlow()

    init {
        try {
            viewModelScope.launch {
                recipeState.value = recipeCollection.document(recipeId).get().await().toObject<Recipe>()!!

                userState.value = userCollection.document(recipeState.value.userId).get().await().toObject<User>()!!
            }
        } catch (exc: Exception) {
            exceptionHandler(exc)
        }
    }

    suspend fun getOwner(): User {
        recipeState.first { it.id == recipeId }

        println("recipe: ${recipe.value.name}, (${recipe.value.id})")

        return userCollection
            .document(recipe.value.userId)
            .get()
            .await()
            .toObject(User::class.java) ?: User()
    }

    private fun exceptionHandler(exc: Exception) {
        Toast.makeText(context, exc.message, Toast.LENGTH_SHORT).show()
    }
}
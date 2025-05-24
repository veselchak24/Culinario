package com.culinario.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.culinario.helpers.RECIPE_COLLECTION
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.persistentCacheSettings
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RecipePageViewModel(
    private val recipeId: String
) : ViewModel() {
    private val recipeCollection = Firebase.firestore.collection(RECIPE_COLLECTION)
    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)

    var recipeState = MutableStateFlow(Recipe())
    var userState = MutableStateFlow(User())
    var currentUser = MutableStateFlow(User())

    var isRecipeLiked = MutableStateFlow(false)

    init {
        if (recipeState.value.id != recipeId) {
            viewModelScope.launch {
                currentUser.value = userCollection.document(Firebase.auth.currentUser!!.uid).get().await().toObject<User>()!!

                isRecipeLiked.value = currentUser.value.likedRecipesId.contains(recipeId)

                recipeState.value = recipeCollection.document(recipeId).get().await().toObject<Recipe>()!!

                userState.value = userCollection.document(recipeState.value.userId).get().await().toObject<User>()!!

                collectRecipeState()
            }
        }
    }

    private fun collectRecipeState() {
        recipeCollection
            .document(recipeId)
            .addSnapshotListener { value, error ->
                if (error == null) {
                    recipeState.value = value!!.toObject<Recipe>()!!
                } else {
                    println(error)
                }
            }
    }

    suspend fun watchedRecipe() {
        recipeState.first { it.id.isNotEmpty() }

        recipeState.value.otherInfo.watches++

        recipeCollection
            .document(recipeId)
            .update("otherInfo.watches", recipeState.value.otherInfo.watches)
    }

    suspend fun toggleLike(response: (isLiked: Boolean) -> Unit = { }) {
        recipeState.first { it.id.isNotEmpty() }
        currentUser.first { it.id.isNotEmpty() }

        isRecipeLiked.value = currentUser.value.likedRecipesId.contains(recipeState.value.id)

        if (isRecipeLiked.value) {
            currentUser.value.likedRecipesId -= recipeId
            recipeState.value.otherInfo.likes--

            isRecipeLiked.value = false
        } else {
            currentUser.value.likedRecipesId += recipeId
            recipeState.value.otherInfo.likes++

            isRecipeLiked.value = true
        }

        recipeCollection
            .document(recipeId)
            .update("otherInfo.likes", recipeState.value.otherInfo.likes)
            .await()

        userCollection
            .document(Firebase.auth.currentUser!!.uid)
            .update("likedRecipesId", currentUser.value.likedRecipesId)
            .await()

        response(isRecipeLiked.value)
    }
}
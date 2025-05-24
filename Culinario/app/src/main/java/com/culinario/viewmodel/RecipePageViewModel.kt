package com.culinario.viewmodel

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

    var isRecipeLiked = MutableStateFlow(false)

    init {
        if (recipeState.value.id != recipeId) {
            viewModelScope.launch {
                recipeState.value = recipeCollection.document(recipeId).get().await().toObject<Recipe>()!!

                userState.value = userCollection.document(recipeState.value.userId).get().await().toObject<User>()!!

                isRecipeLiked.value = userState.value.likedRecipesId.contains(recipeState.value.id)
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
        userState.first { it.id.isNotEmpty() }
        recipeState.first { it.id.isNotEmpty() }

        var isSuccess = true

        isRecipeLiked.value = userState.value.likedRecipesId.contains(recipeState.value.id)

        if (isRecipeLiked.value) {
            userState.value.likedRecipesId -= recipeId
            recipeState.value.otherInfo.likes--

            isRecipeLiked.value = !isRecipeLiked.value
        } else {
            userState.value.likedRecipesId += recipeId
            recipeState.value.otherInfo.likes++

            isRecipeLiked.value = !isRecipeLiked.value
        }

        recipeCollection
            .document(recipeId)
            .update("otherInfo.likes", recipeState.value.otherInfo.likes)
            .await()

        userCollection
            .document(userState.value.id)
            .update("likedRecipesId", userState.value.likedRecipesId)
            .await()

        response(isRecipeLiked.value)
    }
}
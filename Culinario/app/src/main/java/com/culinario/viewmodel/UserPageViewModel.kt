package com.culinario.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.culinario.helpers.RECIPE_COLLECTION
import com.culinario.helpers.StorageUploader
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserPageViewModel(
    val userId: String,
    private val context: Context
) : ViewModel() {
    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)
    private val recipeCollection = Firebase.firestore.collection(RECIPE_COLLECTION)

    private var userState: MutableStateFlow<User> = MutableStateFlow(User())
    val user = userState.asStateFlow()

    private val recipesState = MutableStateFlow(mutableListOf<Recipe>())
    val recipes = recipesState.asStateFlow()

    private val storageUploader = StorageUploader(context)

    init {
        viewModelScope.launch {
            if (user.value.id != userId) {
                userState.value = userCollection
                    .document(userId)
                    .get()
                    .await()
                    .toObject<User>() ?: User()

                userCollection
                    .document(userId)
                    .addSnapshotListener { task, error ->
                        if (error == null && task != null) {
                            userState.value = task.toObject<User>() ?: User()
                        } else {
                            println(error)
                        }
                    }
            }
        }
    }

    val isCurrentUser: Boolean
        get() = Firebase.auth.currentUser?.uid == userId

    suspend fun getUserRecipesAsList(): MutableList<Recipe> {
        userState.first { it.id == userId }

        println("user: ${user.value.name}, (${user.value.id})")
        println(userState.value.recipesId)

        val recipes = mutableListOf<Recipe>()
        userState.value.recipesId.forEach {
            recipes.add(recipeCollection.document(it).get().await().toObject<Recipe>()!!)
        }

        recipesState.value = recipes

        return recipesState.value
    }

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipe.userId = userState.value.id

            recipeCollection
                .document(recipe.id)
                .set(recipe)

            userState.value.recipesId += recipe.id

            userCollection
                .document(userState.value.id)
                .set(userState.value)
        }
    }

    suspend fun loadImage(uri: Uri) {
        storageUploader.uploadImage(uri) {
            userCollection
                .document(userId)
                .update("imageUrl", it)
        }
    }

    suspend fun getRecipesCount(): Int {
        recipesState.first { it.isNotEmpty() }

        return recipesState.value.count { true }
    }
}
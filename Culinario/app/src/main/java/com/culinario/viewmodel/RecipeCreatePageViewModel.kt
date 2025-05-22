package com.culinario.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.culinario.helpers.RECIPE_COLLECTION
import com.culinario.helpers.StorageUploader
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecipeCreatePageViewModel (
    private val userId: String,
    private val context: Context
) : ViewModel(){
    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)
    private val recipeCollection = Firebase.firestore.collection(RECIPE_COLLECTION)

    private var userState: MutableStateFlow<User> = MutableStateFlow(User())

    private var uploader: StorageUploader = StorageUploader(context)

    init {
        if (userState.value.id != userId) {
            userCollection
                .document(userId)
                .get()
                .addOnCompleteListener { documentSnapshot ->
                    if (documentSnapshot.isComplete) {
                        userState.value = documentSnapshot.result.toObject(User::class.java) ?: User()
                    } else {
                        println("Something went wrong")
                    }
                }
        }
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

    fun uploadImage(uri: Uri, onComplete: (String) -> Unit) {
        viewModelScope.launch {
            try {
                uploader.uploadImage(uri) {
                    onComplete(it)
                }
            } catch (e: Exception) {
                Log.e("exception", "Ошибка: ${e.message}")
            }
        }
    }
}
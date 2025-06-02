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
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.reflect.Field

class RecipeCreatePageViewModel (
    userId: String,
    context: Context
) : ViewModel() {
    private val recipeState = MutableStateFlow(Recipe())
    val recipe = recipeState.asStateFlow()

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

    fun updateRecipe(update: (Recipe) -> Recipe) {
        recipeState.update {
            println(it.name)
            update(recipeState.value)
        }
    }

    fun uploadRecipe(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val recipeDocument = recipeCollection.document()
            val userDocument = userCollection.document(userState.value.id)

            val batch = Firebase.firestore.batch()

            batch.set(recipeDocument, recipe.value)
            batch.update(recipeDocument, "id", recipeDocument.id)
            batch.update(recipeDocument, "userId", userDocument.id)

            batch.update(userDocument, "recipesId", FieldValue.arrayUnion(recipeDocument.id))

            batch.commit().addOnSuccessListener {
                onSuccess()
            }
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
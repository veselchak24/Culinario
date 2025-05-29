package com.culinario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import aws.smithy.kotlin.runtime.util.type
import com.culinario.helpers.RECIPE_COLLECTION
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RecipeCardViewModel(
    private val recipeId: String
) : ViewModel() {
    private val recipeCollection = Firebase.firestore.collection(RECIPE_COLLECTION)
    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)

    private val recipeDocument = recipeCollection.document(recipeId)
    private val currentUserDocument = userCollection.document(Firebase.auth.currentUser!!.uid)
    private lateinit var ownerUserDocument: DocumentReference

    private var recipeState: MutableStateFlow<Recipe?> = MutableStateFlow(Recipe())
    var recipe = recipeState.asStateFlow()

    private var ownerUserState: MutableStateFlow<User> = MutableStateFlow(User())
    var user = ownerUserState.asStateFlow()

    private var isRecipeLikedState = MutableStateFlow(false)
    var isRecipeLiked = isRecipeLikedState.asStateFlow()

    private var isCurrentUserState = MutableStateFlow(false)
    var isCurrentUser = isCurrentUserState.asStateFlow()

    init {
        try {
            if (recipe.value!!.id != recipeId) {
                viewModelScope.launch {
                    recipeState.value = recipeDocument.get().await().toObject<Recipe>()!!
                    ownerUserDocument = userCollection.document(recipeState.value!!.userId)
                    ownerUserState.value = ownerUserDocument.get().await().toObject<User>()!!

                    isRecipeLikedState.value = currentUserDocument.get().await().toObject<User>()!!.likedRecipesId.contains(recipeId)
                    isCurrentUserState.value = recipeState.value!!.userId == Firebase.auth.currentUser!!.uid
                }
            }
        } catch (exc: Exception) {
            println(exc.message)
        }
    }

    suspend fun toggleLike(response: (isLiked: Boolean) -> Unit = { }) {
        recipeState.first { it!!.id.isNotEmpty() }

        isRecipeLikedState.value = currentUserDocument.get().await().toObject<User>()!!.likedRecipesId.contains(recipeId)

        if (isRecipeLikedState.value) {
            recipeDocument.update("otherInfo.likes", FieldValue.increment(-1)).await()
            currentUserDocument.update("likedRecipesId", FieldValue.arrayRemove(recipeState.value!!.id)).await()
        } else {
            recipeDocument.update("otherInfo.likes", FieldValue.increment(1)).await()
            currentUserDocument.update("likedRecipesId", FieldValue.arrayUnion(recipeState.value!!.id)).await()
        }

        isRecipeLikedState.value = !isRecipeLikedState.value

        response(isRecipeLikedState.value)
    }

    fun deleteSelfFromDatabase(onDelete: () -> Unit) {
        viewModelScope.launch {
            currentUserDocument.update("recipesId", FieldValue.arrayRemove(recipeId))

            val batch = Firebase.firestore.batch()

            userCollection
                .get()
                .addOnSuccessListener { snapshot ->
                    snapshot.documents.forEach { doc ->
                        val documentReference = userCollection.document(doc.id)
                        batch.update(documentReference, "likedRecipesId", FieldValue.arrayRemove(recipeId))
                    }

                    batch.delete(recipeDocument)

                    batch.commit().addOnSuccessListener {
                        onDelete()
                    }
                }
        }
    }
}
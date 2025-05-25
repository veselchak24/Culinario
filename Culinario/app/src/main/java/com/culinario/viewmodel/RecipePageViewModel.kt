package com.culinario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.culinario.helpers.COMMENTARY_COLLECTION
import com.culinario.helpers.RECIPE_COLLECTION
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.Commentary
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RecipePageViewModel(
    private val recipeId: String
) : ViewModel() {
    private val recipeCollection = Firebase.firestore.collection(RECIPE_COLLECTION)
    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)
    private val commentaryCollection = Firebase.firestore.collection(COMMENTARY_COLLECTION)

    private lateinit var recipeDocument: DocumentReference
    private lateinit var ownerUserDocument: DocumentReference
    private lateinit var currentUserDocument: DocumentReference

    private var recipeState = MutableStateFlow(Recipe())
    val recipe = recipeState.asStateFlow()

    private var currentUserState = MutableStateFlow(User())
    val currentUser = currentUserState.asStateFlow()

    private var ownerUserState = MutableStateFlow(User())
    val ownerUser = ownerUserState.asStateFlow()

    var isRecipeLiked = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            initDocuments()
            initStates()

            isRecipeLiked.value = currentUserState.value.likedRecipesId.contains(recipeId)

            trackRecipeState()
            trackCurrentUserState()
        }
    }

    private suspend fun initDocuments() {
        recipeDocument = recipeCollection.document(recipeId)
        currentUserDocument = userCollection.document(Firebase.auth.currentUser!!.uid)
        ownerUserDocument =
            userCollection.document(recipeDocument.get().await().get("userId").toString())
    }

    private suspend fun initStates() {
        currentUserState.value = currentUserDocument.get().await().toObject<User>()!!
        recipeState.value = recipeDocument.get().await().toObject<Recipe>()!!
        ownerUserState.value = ownerUserDocument.get().await().toObject<User>()!!
    }

    private fun trackRecipeState() {
        recipeDocument
            .addSnapshotListener { value, error ->
                if (error == null) {
                    recipeState.value = value!!.toObject<Recipe>()!!
                } else {
                    println(error)
                }
            }
    }

    private fun trackCurrentUserState() {
        currentUserDocument
            .addSnapshotListener { value, error ->
                if (error == null) {
                    currentUserState.value = value!!.toObject<User>()!!
                } else {
                    println(error)
                }
            }
    }

    fun watchedRecipe() {
        recipeDocument
            .update("otherInfo.watches", FieldValue.increment(1))
    }

    suspend fun toggleLike(response: (isLiked: Boolean) -> Unit = { }) {
        waitForInit()

        isRecipeLiked.value = currentUserState.value.likedRecipesId.contains(recipeState.value.id)

        if (isRecipeLiked.value) {
            currentUserDocument.update("likedRecipesId", FieldValue.arrayRemove(recipeState.value.id)).await()
            recipeDocument.update("otherInfo.likes", FieldValue.increment(-1)).await()
        } else {
            currentUserDocument.update("likedRecipesId", FieldValue.arrayUnion(recipeState.value.id)).await()
            recipeDocument.update("otherInfo.likes", FieldValue.increment(1)).await()
        }

        isRecipeLiked.value = !isRecipeLiked.value

        response(isRecipeLiked.value)
    }

    suspend fun sendCommentary(text: String, onSent: () -> Unit = { }) {
        waitForInit()

        val newCommentary = commentaryCollection.document()

        newCommentary.set(
            Commentary(
                id = newCommentary.id,
                userId = currentUserState.value.id,
                text = text,
                date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yy")),
                likes = 0
            )
        ).await()

        recipeDocument
            .update("commentaries", FieldValue.arrayUnion(newCommentary.id))
            .await()

        onSent()
    }

    private suspend fun waitForInit() {
        recipeState.first { it.id.isNotEmpty() }
        currentUserState.first { it.id.isNotEmpty() }
    }
}
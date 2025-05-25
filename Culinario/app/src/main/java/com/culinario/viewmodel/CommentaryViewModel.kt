package com.culinario.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.culinario.helpers.COMMENTARY_COLLECTION
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.Commentary
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

class CommentaryViewModel(
    private val commentaryId: String
) : ViewModel() {
    private val usersCollection = Firebase.firestore.collection(USER_COLLECTION)

    private val currentUserDocument = usersCollection.document(Firebase.auth.currentUser!!.uid)
    private lateinit var ownerUserDocument: DocumentReference

    private val commentaryDocument = Firebase.firestore.collection(COMMENTARY_COLLECTION).document(commentaryId)

    private val currentUserState = MutableStateFlow(User())

    private val ownerUserState = MutableStateFlow(User())
    val ownerUser = ownerUserState.asStateFlow()

    private val commentaryState = MutableStateFlow(Commentary())
    val commentary = commentaryState.asStateFlow()

    var isLiked = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            commentaryState.value = commentaryDocument.get().await().toObject<Commentary>() ?: Commentary()
            currentUserState.value = currentUserDocument.get().await().toObject<User>() ?: User()

            ownerUserDocument = usersCollection.document(commentaryState.value.userId)
            ownerUserState.value = ownerUserDocument.get().await().toObject<User>() ?: User()

            isLiked.value = currentUserState.value.likedCommentariesId.contains(commentary.value.id)

            collectCurrentUserState()
            collectCommentaryState()
        }
    }

    private fun collectCurrentUserState() {
        currentUserDocument
            .addSnapshotListener { task, error ->
                if (error == null) {
                    currentUserState.value = task?.toObject<User>() ?: User()
                } else {
                    Log.e("exception", error.message ?: "unknown error")
                }
            }
    }

    private fun collectCommentaryState() {
        commentaryDocument
            .addSnapshotListener { task, error ->
                if (error == null) {
                    commentaryState.value = task?.toObject<Commentary>() ?: Commentary()
                } else {
                    Log.e("exception", error.message ?: "unknown error")
                }
            }
    }

    suspend fun toggleLike(response: (isLiked: Boolean) -> Unit) {
        waitForInit()

        isLiked.value = currentUserState.value.likedCommentariesId.contains(commentaryId)

        if (isLiked.value) {
            currentUserDocument.update("likedCommentariesId", FieldValue.arrayRemove(commentaryId))
            commentaryDocument.update("likes", FieldValue.increment(-1))
        } else {
            currentUserDocument.update("likedCommentariesId", FieldValue.arrayUnion(commentaryId))
            commentaryDocument.update("likes", FieldValue.increment(1))
        }

        isLiked.value = !isLiked.value

        response(isLiked.value)
    }

    private suspend fun waitForInit() {
        currentUserState.first { it.id.isNotEmpty() }
        commentaryState.first { it.id.isNotEmpty() }
    }
}
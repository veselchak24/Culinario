package com.culinario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FavoritePageViewModel : ViewModel() {
    private val currentUserDocument = Firebase.firestore.collection(USER_COLLECTION).document(Firebase.auth.currentUser!!.uid)

    private val currentUserState = MutableStateFlow(User())
    val currentUser = currentUserState.asStateFlow()

    init {
        viewModelScope.launch {
            currentUserState.value = currentUserDocument.get().await().toObject<User>() ?: User()

            collectUserState()
        }
    }

    private fun collectUserState() {
        currentUserDocument
            .addSnapshotListener { task, error ->
                if (error == null && task != null) {
                    currentUserState.value = task.toObject<User>() ?: User()
                } else {
                    println(error)
                }
            }
    }
}
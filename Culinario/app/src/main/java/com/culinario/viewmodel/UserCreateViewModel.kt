package com.culinario.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserCreateViewModel(
    private val userId: String
) : ViewModel() {
    var userLoaded = mutableStateOf(false)

    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)

    val userState = MutableStateFlow(User())
    val user = userState.asStateFlow()

    init {
        viewModelScope.launch {
            userState.value = userCollection
                .document(userId)
                .get()
                .await()
                .toObject(User::class.java) ?: User()

            userLoaded.value = true
        }
    }


}
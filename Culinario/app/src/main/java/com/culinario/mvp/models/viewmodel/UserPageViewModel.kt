package com.culinario.mvp.models.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class UserPageViewModel(
    private val userId: String,
    private val context: Context
) : ViewModel() {
    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)

    private var currentUser: MutableState<User> = mutableStateOf(User())

    init {
        println("userPageViewModel init")

        try {
            userCollection
                .document(userId)
                .get()
                .addOnCompleteListener { documentSnapshot ->
                    if (documentSnapshot.isComplete) {
                        currentUser.value = documentSnapshot.result.toObject(User::class.java) ?: User()
                    } else {
                        println("Something went wrong")
                    }
                }
        } catch (ex: Exception) {
            Log.e("data", ex.message ?: "exception thrown")
        }
    }

    val isCurrentUser: Boolean
        get() = Firebase.auth.currentUser?.uid == userId

    fun getCurrentUser(): MutableState<User> = currentUser
}
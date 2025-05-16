package com.culinario.mvp.models.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class UserPageViewModel : ViewModel() {
    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)
    private val auth: FirebaseAuth = Firebase.auth

    private var currentUser: MutableState<User> = mutableStateOf(User())

    fun getCurrentUser(): MutableState<User> {
        auth.currentUser?.let {
            userCollection
                .document(it.uid)
                .get()
                .addOnCompleteListener { documentSnapshot ->
                    if (documentSnapshot.isComplete) {
                        currentUser.value = documentSnapshot.result.toObject(User::class.java) ?: User()
                    } else {
                        println("Something went wrong")
                    }
                }
        }

        return currentUser
    }
}
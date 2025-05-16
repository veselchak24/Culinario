package com.culinario.mvp.models.viewmodel

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

    private lateinit var currentUser: User

    fun getCurrentUser(): User? {
        auth.currentUser?.let {
            userCollection
                .document(it.uid)
                .get()
                .addOnCompleteListener { documentSnapshot ->
                    if (documentSnapshot.isComplete) {
                        val currentUser =  documentSnapshot.result.toObject(User::class.java)

                        return@addOnCompleteListener
                    } else {
                        println("Something went wrong")
                    }
                }
        }

        return null
    }
}
package com.culinario.viewmodel

import androidx.lifecycle.ViewModel
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class LoginViewModel : ViewModel() {
    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    lateinit var onException: (Exception) -> Unit

    fun signIn(email: String, password: String, onSignIn: (FirebaseUser?) -> Unit) {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    val currentUser = auth.currentUser

                    if (it.isComplete && currentUser != null) {
                        onSignIn(currentUser)
                    } else {
                        onException(IllegalArgumentException())
                    }
                }
        } catch (e: Exception) {
            onException(e)
        }
    }

    fun signUp(nickname: String, email: String, password: String, onSignUp: (FirebaseUser?) -> Unit) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    val currentUser = auth.currentUser

                    if (it.isComplete && currentUser != null) {

                        val localUser = User (
                            id = currentUser.uid,
                            name = nickname,
                            email = currentUser.email ?: "null"
                        )

                        firestore
                            .collection(USER_COLLECTION)
                            .document(auth.currentUser!!.uid)
                            .set(localUser)

                        onSignUp(currentUser)
                    } else {
                        throw IllegalArgumentException()
                    }
                }
        } catch (e: Exception) {
            onException(e)
        }
    }

    fun onGoogleAuth(firebaseUser: FirebaseUser, onSignIn: (FirebaseUser) -> Unit) {
        try {
            firestore
                .collection(USER_COLLECTION)
                .document(firebaseUser.uid)
                .set(
                    User(
                        id = firebaseUser.uid,
                        name = firebaseUser.displayName ?: "unknown user",
                        email = firebaseUser.email ?: "null",
                        imageUrl = firebaseUser.photoUrl.toString()
                    )
                )

            onSignIn(auth.currentUser!!)
        } catch (e: Exception) {
            onException(e)
        }
    }
}
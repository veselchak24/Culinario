package com.culinario.viewmodel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.culinario.helpers.StorageUploader
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserCreateViewModel(
    private val userId: String,
    private val context: Context
) : ViewModel() {
    var userLoaded = mutableStateOf(false)

    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)
    private val uploader = StorageUploader(context)

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

    fun saveUser(user: User, saved: () -> Unit) {
        viewModelScope.launch {
            userCollection
                .document(userId)
                .set(user)
                .await()

            saved()
        }
    }

    fun uploadImage(uri: Uri, uploaded: (url: String) -> Unit) {
        viewModelScope.launch {
            try {
                uploader.uploadImage(uri) {
                    uploaded(it)
                }
            } catch (exc: Exception) {
                Toast.makeText(context, "Что-то пошло не по плану при отгрузке картинки..", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
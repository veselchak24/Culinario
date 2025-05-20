package com.culinario.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import com.culinario.helpers.StorageUploader
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.ObjectCannedAcl
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.content.ByteStream
import aws.smithy.kotlin.runtime.content.fromFile
import aws.smithy.kotlin.runtime.net.url.Url
import com.culinario.helpers.RECIPE_COLLECTION
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.google.common.io.FileWriteMode
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.File

class RecipeCreatePageViewModel (
    private val userId: String,
    private val context: Context
) : ViewModel(){
    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)
    private val recipeCollection = Firebase.firestore.collection(RECIPE_COLLECTION)

    private var userState: MutableStateFlow<User> = MutableStateFlow(User())

    init {
        if (userState.value.id != userId) {
            userCollection
                .document(userId)
                .get()
                .addOnCompleteListener { documentSnapshot ->
                    if (documentSnapshot.isComplete) {
                        userState.value = documentSnapshot.result.toObject(User::class.java) ?: User()
                    } else {
                        println("Something went wrong")
                    }
                }
        }
    }

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipe.userId = userState.value.id

            recipeCollection
                .document(recipe.id)
                .set(recipe)

            userState.value.recipesId += recipe.id

            userCollection
                .document(userState.value.id)
                .set(userState.value)
        }
    }

    private val bucket = "culinario-resources"

    fun uploadSampleFile(uri: Uri, onComplete: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val s3Client = S3Client {
                    region = "ru-msk"
                    credentialsProvider = StaticCredentialsProvider {
                        accessKeyId = "oJmXx8AUQKVrqTgXUJFfiH"
                        secretAccessKey = "bSvLEjC32a5FqhgJy3Gwggjc4znTNpvLoi1FS4Au4uR5"
                    }
                    endpointUrl = Url.parse("https://hb.vkcloud-storage.ru")
                }

                val key = "images/img_${System.currentTimeMillis()}.png"

                context.contentResolver.openInputStream(uri).use {
                    val request = PutObjectRequest {
                        this.bucket = "culinario-resources"
                        this.key = key
                        body = ByteStream.fromBytes(it!!.readBytes())
                        acl = ObjectCannedAcl.PublicRead
                    }

                    s3Client.putObject(request)
                }

                onComplete(getPublicLink(key))
            } catch (e: Exception) {
                Log.e("exception", "Ошибка: ${e.message}")
            }
        }
    }

    private fun getPublicLink(
        key: String,
        bucket: String = "culinario-resources"
    ) = "https://$bucket.hb.ru-msk.vkcloud-storage.ru/$key"
}
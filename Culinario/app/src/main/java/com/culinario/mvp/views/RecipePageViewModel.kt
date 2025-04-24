package com.culinario.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.culinario.R
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.culinario.mvp.models.imageloader.ImageLoader
import com.culinario.mvp.models.imageloader.ResourceImageLoader
import com.culinario.mvp.models.imageloader.UriImageLoader
import com.culinario.mvp.models.repository.user.UserRepository
import com.culinario.mvp.repository.recipe.RecipeRepository

class RecipePageViewModel (
    private val recipeId: String,
    val recipeRepository: RecipeRepository,
    val userRepository: UserRepository,
    val context: Context
) {
    private var imageLoader: ImageLoader
    private var recipe: Recipe

    init {
        recipe = getRecipe()

        imageLoader =
        if (recipe.recipeImageResources.recipeBackgroundImageResources != null)
            ResourceImageLoader (
                imageResourceId = recipe.recipeImageResources.recipeBackgroundImageResources,
                imageResourcesId = recipe.recipeImageResources.recipePicturesResources,
                context = context
            )
        else
            UriImageLoader (
                uri = Uri.parse(recipe.recipeImageResources.recipeBackgroundImageUri),
                uris = recipe.recipeImageResources.recipePicturesUri?.map { Uri.parse(it) }?.toTypedArray(),
                context = context
            )
    }

    fun getRecipe(): Recipe {
        return recipeRepository.getRecipeById(recipeId)
    }

    fun getUserOwner(): User {
        return userRepository.getUserById(getRecipe().userId)
    }

    fun getBackgroundBitmap(): Bitmap {
        return imageLoader.loadImage() ?: BitmapFactory.decodeResource(context.resources, R.drawable.baseline_heart_broken_24)
    }

    fun getArrayBitmaps(): Array<Bitmap> {
        return imageLoader.loadImages().map {
            it!!
        }.toTypedArray()
    }
}
package com.culinario.mvp.models

data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var about: String = "",
    var imageUrl: String? = null,
    var backgroundImageUrl: String? = null,
    var recipesId: List<String> = listOf(),
    var likedRecipesId: List<String> = listOf(),
    var likedCommentariesId: List<String> = listOf(),
)
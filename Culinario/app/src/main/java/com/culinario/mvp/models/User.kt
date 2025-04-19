package com.culinario.mvp.models

// source: Culinario_DB/EFCore/Models/UserModel.cs

data class User(
    val Id: Int,
    var Name: String,
    var Email: String? = null,
    var UserImage: Image? = null,
    var Recipes: List<Recipe>? = null,
    var FavoriteRecipes: List<Recipe>? = null,
)

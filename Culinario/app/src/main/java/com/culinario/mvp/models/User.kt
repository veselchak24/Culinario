package com.culinario.mvp.models

data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var about: String = "",
    var recipesId: List<String> = listOf(),
    var imageUrl: String? = null
)
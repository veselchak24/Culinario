package com.culinario.mvp.models

import android.net.Uri

data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var about: String = "",
    var recipesId: List<String> = listOf(),
    var imageUrl: Uri? = null
)
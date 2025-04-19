package com.culinario.mvp.models

import androidx.annotation.Size
import kotlinx.serialization.Serializable

@Serializable
data class RecipeCategory(
    override val Id: Int,
    @Size(max = 20) val Name: String,
) : IModel
package com.culinario.helpers

import android.content.Context
import android.graphics.Bitmap

class RecipeSaveHelper (
    private val context: Context
) {
    fun saveBitmapToFile(bitmap: Bitmap) {
        println("Current bitmap hash: ${bitmap.hashCode()}")
    }
}
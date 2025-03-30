package com.culinario.helpers

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import java.io.File

class RecipeSaveHelper (
    private val context: Context
) {
    fun saveBitmapToFile(bitmap: Bitmap): String {
        val file = File(context.applicationContext.filesDir, "img_${bitmap.hashCode()}.png")
        bitmap.compress(CompressFormat.PNG, 70, context.openFileOutput(file.name, MODE_PRIVATE))

        println("Bitmap saved to: ${file.path}")

        return file.path
    }
}
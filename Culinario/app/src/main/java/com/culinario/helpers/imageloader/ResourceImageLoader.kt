package com.culinario.helpers.imageloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class ResourceImageLoader (
    val imageResourceId: Int? = null,
    val imageResourcesId: Array<Int>? = null,
    val context: Context
)  : ImageLoader(context) {

    override fun loadImage(): Bitmap? {
        if (imageResourceId == null) return null

        return loadImage(imageResourceId)
    }

    override fun loadImages(): Array<Bitmap?> {
        if (imageResourcesId == null) return arrayOf()

        return imageResourcesId.map {
            loadImage(it)
        }.toTypedArray()
    }

    private fun loadImage(id: Int): Bitmap? =
        BitmapFactory.decodeStream(context.resources.openRawResource(id))
}
package com.culinario.mvp.models.imageloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

class UriImageLoader (
    val uri: Uri? = null,
    val uris: Array<Uri>? = null,
    val context: Context
) : ImageLoader(context) {

    override fun loadImage(): Bitmap? {
        if (uri == null) return null

        return loadImage(uri)
    }

    override fun loadImages(): Array<Bitmap?> {
        if (uris == null) return arrayOf()

        return uris.map {
            loadImage(it)
        }.toTypedArray()
    }

    private fun loadImage(uri: Uri): Bitmap =
        BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))
}
package com.culinario.helpers.imageloader

import android.content.Context
import android.graphics.Bitmap

abstract class ImageLoader (
    context: Context
) {
    abstract fun loadImage(): Bitmap?
    abstract fun loadImages(): Array<Bitmap?>
}
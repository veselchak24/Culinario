package com.culinario.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.ByteArrayOutputStream

fun loadAndCompressImage(
    context: Context,
    it: Uri?,
): Bitmap? {
    val inputSteam = context.contentResolver.openInputStream(it!!)

    val sourceBitmap = BitmapFactory.decodeStream(inputSteam)
    val byteArrayStream = ByteArrayOutputStream()

    sourceBitmap.compress(CompressFormat.JPEG, 20, byteArrayStream)

    val compressedImageByteArray = byteArrayStream.toByteArray()

    val resultBitmap = BitmapFactory.decodeByteArray(compressedImageByteArray, 0, compressedImageByteArray.size)

    inputSteam?.close()

    return resultBitmap
}

private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    val height = options.outHeight
    val width = options.outWidth
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {
        val halfHeight = height / 2
        val halfWidth = width / 2

        while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
            inSampleSize *= 2
        }
    }

    return inSampleSize
}
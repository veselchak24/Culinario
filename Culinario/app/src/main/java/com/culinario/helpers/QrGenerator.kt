package com.culinario.helpers

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter

fun generateQRCode(text: String, size: Int = 512): Bitmap {
    val bitMatrix = MultiFormatWriter().encode(
        text,
        BarcodeFormat.QR_CODE,
        size,
        size,
        null
    )

    val width = bitMatrix.width
    val height = bitMatrix.height
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

    for (x in 0 until width) {
        for (y in 0 until height) {
            bitmap.setPixel(x, y, if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())
        }
    }

    return bitmap
}
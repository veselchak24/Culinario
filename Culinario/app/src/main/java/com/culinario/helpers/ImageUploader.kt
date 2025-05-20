package com.culinario.helpers

import android.content.Context
import android.net.Uri
import android.widget.Toast
import io.minio.MinioClient
import io.minio.PutObjectArgs

class ImageUploader(
    private val context: Context
) {
    private var minioClient: MinioClient = MinioClient
        .builder()
        .endpoint("https://storage.yandexcloud.net")
        .credentials(
            "YCAJEmhx-bJ0jucynkKFt4BzC",
            "YCMjA0Cr53XQTEZsn0IbWS8AM6QEosGCYWxyoDlE"
        )
        .build()

    fun uploadImage(uri: Uri, fileName: String) {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val fileSize = context.contentResolver.openAssetFileDescriptor(uri, "r")?.use {
                it.length
            } ?: 0L

            minioClient.putObject(
                PutObjectArgs
                    .builder()
                    .bucket("culinario-resources")
                    .contentType("image/jpeg")
                    .stream(inputStream, fileSize, -1)
                    .`object`(fileName)
                    .build()
            )

        } catch (exc: Exception) {
            Toast.makeText(context, "Error while loading image", Toast.LENGTH_LONG).show()
        }
    }
}
package com.culinario.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.ObjectCannedAcl
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.content.ByteStream
import aws.smithy.kotlin.runtime.net.url.Url
import kotlinx.coroutines.coroutineScope
import java.io.ByteArrayOutputStream

class StorageUploader(
    private val context: Context
) {
    private val s3Client = S3Client {
        region = "ru-msk"
        credentialsProvider = StaticCredentialsProvider {
            accessKeyId = "oJmXx8AUQKVrqTgXUJFfiH"
            secretAccessKey = "bSvLEjC32a5FqhgJy3Gwggjc4znTNpvLoi1FS4Au4uR5"
        }
        endpointUrl = Url.parse("https://hb.vkcloud-storage.ru")
    }

    suspend fun uploadImage(uri: Uri, imageCompressPercent: Int = 60, onUpload: (url: String) -> Unit) {
        coroutineScope {
            val key = "images/img_${System.currentTimeMillis()}.png"

            val bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, imageCompressPercent, outputStream)

            outputStream.use {
                val request = PutObjectRequest {
                    this.bucket = "culinario-resources"
                    this.key = key
                    body = ByteStream.fromBytes(it.toByteArray())
                    acl = ObjectCannedAcl.PublicRead
                }

                s3Client.putObject(request)

                onUpload(getPublicLink(key))
            }
        }
    }

    fun getPublicLink(
        key: String,
        bucket: String = "culinario-resources"
    ): String = "https://$bucket.hb.ru-msk.vkcloud-storage.ru/$key"
}
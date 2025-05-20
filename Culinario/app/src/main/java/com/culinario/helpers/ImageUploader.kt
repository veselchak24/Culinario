package com.culinario.helpers

import io.minio.MinioClient

class ImageUploader {

    fun createClient() {
        val minioClient = MinioClient.builder()
            .endpoint("https://storage.yandexcloud.net")
            .credentials("YCAJEmhx-bJ0jucynkKFt4BzC", "YCMjA0Cr53XQTEZsn0IbWS8AM6QEosGCYWxyoDlE")
            .build()

    }
}
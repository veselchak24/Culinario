package com.culinario.helpers

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.ByteArrayOutputStream
import java.io.IOException

fun sendBitmapToBackend(
    isProcessing: MutableState<Boolean>,
    bitmap: Bitmap,
    detectedFruits: MutableState<List<String>>,
) {
    val byteArray = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray)
    val requestBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart(
            "file",
            "image.jpg",
            byteArray.toByteArray().toRequestBody("image/jpeg".toMediaTypeOrNull())
        )
        .build()

    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://culinarioai.anopka.keenetic.pro/predict/")
        .post(requestBody)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("Error: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) return

            val responseBody = response.body?.string() ?: return

            // Используем Gson для парсинга в Map
            val gson = Gson()
            val type = object : TypeToken<Map<String, Any>>() {}.type
            val responseMap = gson.fromJson<Map<String, Any>>(responseBody, type)

            if (responseMap.containsKey("error")) {
                println("Error: ${responseMap["error"]}")
                isProcessing.value = false
                return
            }

            // Извлекаем список фруктов
            detectedFruits.value = responseMap["fruit"] as? List<String> ?: emptyList()
            isProcessing.value = false
        }
    })
}
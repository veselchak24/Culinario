package com.culinario

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.ByteArrayOutputStream
import java.io.IOException
import okhttp3.*

class CameraActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var captureButton: Button

    // создаём http клиент
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        imageView = findViewById(R.id.imageView)
        captureButton = findViewById(R.id.button)

        captureButton.setOnClickListener {
            requestCameraPermission()

        }
    }

    // запрашиваем разрешение к камере
    private fun requestCameraPermission() {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
        }
    }

    // открываем камеру если все ок
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 101)
    }

    // получаем изображение с камеры
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
            uploadImage(imageBitmap)
        }
    }

    // отправляем изображение по api
    private fun uploadImage(bitmap: Bitmap) {
        // конвертируем изображение в бинарник
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        // создаём тело запроса
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                "image.jpg",
                RequestBody.create("image/jpeg".toMediaTypeOrNull(), byteArray)
            )
            .build()

        // отправляем запрос
        val request = Request.Builder()
            .url("http://87.228.27.211:8000/predict/")
            .post(requestBody)
            .build()


        client.newCall(request).enqueue(object : Callback {
            // вызывается при ошибке запроса
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                println(e.message)
                runOnUiThread {
                    println(e.message)
                    Toast.makeText(this@CameraActivity, "Error: ${e.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }

            // получаем ответ если все ок
            override fun onResponse(call: Call, response: Response)  {
                response.use {
                    if (!it.isSuccessful) throw IOException("Unexpected code $it")
                    val responseString = it.body?.string()
                    println(responseString)

                    runOnUiThread {
                        Toast.makeText(this@CameraActivity, responseString, Toast.LENGTH_LONG).show()

                    }


                }

            }
        })
    }
}
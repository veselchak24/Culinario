package com.culinario.pages

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraPage() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    val previewView = remember { PreviewView(context) }
    val scope = rememberCoroutineScope()
    var photoFile by remember { mutableStateOf<File?>(null) }
    var serverResponse by remember { mutableStateOf<String?>(null) }

    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
        ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        return
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        Log.d("CAMERA", "Photo taken!")
        val currentTimeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val photoPath = "${context.filesDir}/IMG_$currentTimeStamp.jpg"
        photoFile = File(photoPath)
        try {
            photoFile?.outputStream()?.use { outputStream ->
                bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
            uploadToServer(photoFile!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        previewView.modifier.align(Alignment.Center)

        Button(onClick = {
            launcher.launch(null)
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray), modifier = Modifier.align(Alignment.BottomCenter)) {
            Icon(Icons.Default.CameraAlt, contentDescription = "Take Photo", tint = Color.White)
        }
    }

    if (photoFile != null && serverResponse.isNullOrEmpty()) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary, modifier = Modifier.align(Alignment.Center))
    }

    if (!serverResponse.isNullOrBlank()) {
        LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            item {
                Card(modifier = Modifier.fillParentMaxWidth().align(Alignment.CenterHorizontally)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(model = photoFile!!, contentDescription = "Uploaded Image")
                        Text(text = serverResponse!!, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

suspend fun uploadToServer(file: File) {
    val client = OkHttpClient()
    val requestBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("file", file.name, MultipartBody.Part.createFormData("file", file.name, file.readBytes()))
        .build()

    val request = Request.Builder()
        .url("https://server/upload") // Адрес сервера замените на ваш реальный адрес
        .post(requestBody)
        .build()

    val response = client.newCall(request).execute()
    if (response.isSuccessful) {
        val bodyString = response.body!!.string()
        println(bodyString)
    } else {
        throw Exception(response.message)
    }
}
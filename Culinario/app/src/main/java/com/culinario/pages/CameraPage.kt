package com.culinario.pages

import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.culinario.R
import com.culinario.controls.camera.CameraPreview
import com.culinario.controls.camera.TakePhoto
import com.culinario.helpers.ImagePicker

@Composable
@Preview(showBackground = true)
fun CameraPage(
    modifier: Modifier = Modifier,
    onImagePicked: (Bitmap) -> Unit = { },
    detectedFruits: List<String> = emptyList(), // Добавлено
    isProcessing: Boolean = false // Добавлено
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    var isShowingDialog by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )

    LaunchedEffect(key1 = true) {
        launcher.launch(android.Manifest.permission.CAMERA)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (hasCamPermission) {
            if (isShowingDialog) {
                AlertDialog(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.info_icon),
                            contentDescription = "Example Icon"
                        )
                    },
                    title = {
                        Text(
                            text = "Результат распознавания",
                            textAlign = TextAlign.Center
                        )
                    },
                    text = {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (isProcessing) {
                                CircularProgressIndicator()
                            } else {
                                if (detectedFruits.isNotEmpty()) {
                                    Text("Найдено:")

                                    Spacer(Modifier.height(8.dp))

                                    detectedFruits.forEach { fruit ->
                                        Text("${fruit.replaceFirstChar { it.uppercase() }}")
                                    }
                                } else {
                                    Text("Не найдено")
                                }
                            }
                        }
                    },
                    onDismissRequest = { },
                    confirmButton = {
                        Button(
                            onClick = { isShowingDialog = false }
                        ) {
                            Text("OK")
                        }
                    }
                )
            }

            Box(
                modifier = modifier
                    .fillMaxSize()
            ) {
                val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
                val cameraSelector = remember { mutableStateOf(CameraSelector.DEFAULT_BACK_CAMERA) }

                CameraPreview(
                    lifecycleOwner = lifecycleOwner,
                    cameraProviderFuture,
                    modifier = Modifier
                        .fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(30.dp)
                        .fillMaxWidth()
                ) {
                    ImagePicker (
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(
                                start = 40.dp
                            )
                    ) {
                        onImagePicked(it)

                        isShowingDialog = true
                    }

                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .clickable {
                                TakePhoto(
                                    context = context,
                                    lifecycleOwner = lifecycleOwner,
                                    cameraProvider = cameraProviderFuture.get(),
                                    cameraSelector = cameraSelector,
                                    onImageCaptured = { bitmap ->
                                        onImagePicked(bitmap)

                                        isShowingDialog = true
                                    }
                                )
                            }
                            .align(Alignment.BottomCenter)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.camera_icon),
                            contentDescription = "camera icon",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        } else {
            Text(
                text = "Нет разрешения на использование камеры",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}



package com.culinario.pages

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun CameraPage(
    modifier: Modifier
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

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )

    LaunchedEffect(key1 = true) {
        launcher.launch(android.Manifest.permission.CAMERA)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (hasCamPermission) {
            Box (
                modifier = modifier
            ) {
                val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

//                CameraPreview(
//                    lifecycleOwner = lifecycleOwner,
//                    cameraProviderFuture,
//                    modifier = Modifier
//                )
//
//                IconButton (
//                    modifier = Modifier
//                        .align(Alignment.BottomCenter)
//                        .padding(50.dp)
//                        .size(70.dp),
//                    onClick = {
//                        takePhoto(
//                            context = context,
//                            lifecycleOwner = lifecycleOwner,
//                            cameraProvider = cameraProviderFuture.get(),
//                            cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
//                            onImageCaptured = { bitmap ->
//                                //TODO("отправка на сервер")
//                            }
//                        )
//                    }
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.camera_icon),
//                        contentDescription = "Camera button",
//                        modifier = Modifier
//                            .fillMaxSize()
//                    )
//                }
            }
        } else {
            Text(
                text = "Нет разрешения на использование камеры",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}



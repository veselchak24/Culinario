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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    onImagePicked: (Bitmap) -> Unit = { }
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

    var isWaitingResponse by remember { mutableStateOf(false) }

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
            if (isWaitingResponse) {
                AlertDialog(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.info_icon),
                            contentDescription = "Example Icon"
                        )
                    },
                    title = {
                        Text(text = "Жду..")
                    },
                    text = {
                        Text(
                            text = "Запаситесь терпением. Придётся подождать ответ сервера..",
                            textAlign = TextAlign.Center
                        )
                    },
                    onDismissRequest = {
                        isWaitingResponse = false
                    },
                    confirmButton = {},
                    dismissButton = {
                        TextButton(
                            onClick = {
                                isWaitingResponse = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }

            Box (
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

                Row (
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(30.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ImagePicker {
                        onImagePicked(it)

                        isWaitingResponse = true
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

                                        isWaitingResponse = true
                                    }
                                )
                           }
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

                    IconButton(
                        onClick = {
                            cameraSelector.value = if (cameraSelector.value == CameraSelector.DEFAULT_BACK_CAMERA)
                                CameraSelector.DEFAULT_FRONT_CAMERA
                            else
                                CameraSelector.DEFAULT_BACK_CAMERA
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.cameraswitch_icon),
                            contentDescription = "camera switch"
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



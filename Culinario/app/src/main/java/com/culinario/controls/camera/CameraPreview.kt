package com.culinario.controls.camera

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture

@Composable
fun CameraPreview(
    lifecycleOwner: LifecycleOwner,
    cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
    modifier: Modifier
) {
    val context = LocalContext.current
    var preview by remember { mutableStateOf<Preview?>(null) }

    AndroidView(
        factory = { ctx ->
            val viewFinder = PreviewView(ctx)
            val executor = ContextCompat.getMainExecutor(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview
                    )
                } catch(exc: Exception) {
                    Log.e("CameraPreview", "Use case binding failed", exc)
                }
            }, executor)

            viewFinder
        },
        modifier = modifier
    )
}
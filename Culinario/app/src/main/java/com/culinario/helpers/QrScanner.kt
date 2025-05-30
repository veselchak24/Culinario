package com.culinario.helpers

import androidx.activity.ComponentActivity
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

@Composable
fun QrScanner(
    scannedText: (id: String) -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val scanner = remember { BarcodeScanning.getClient() }
    var scanned by remember { mutableStateOf("") }

    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val cameraExecutor = ContextCompat.getMainExecutor(ctx)

            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor) { imageProxy ->
                            processImage(
                                imageProxy = imageProxy,
                                scanner = scanner
                            ) { result ->
                                scanned = result
                                scannedText(result)
                            }
                            imageProxy.close()
                        }
                    }

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    (context as ComponentActivity),
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    imageAnalysis
                )
            }, cameraExecutor)

            previewView
        },
        modifier = Modifier.fillMaxSize()
    )

    Text(scanned)

    content()
}

@OptIn(ExperimentalGetImage::class)
private fun processImage(
    imageProxy: ImageProxy,
    scanner: BarcodeScanner,
    onScanned: (String) -> Unit,
) {
    val mediaImage = imageProxy.image ?: return
    val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

    scanner.process(image)
        .addOnSuccessListener { barcodes ->
            barcodes.firstOrNull()?.rawValue?.let { qrCode ->
                onScanned(qrCode)
            }
        }
        .addOnCompleteListener {
            imageProxy.close()
        }
}
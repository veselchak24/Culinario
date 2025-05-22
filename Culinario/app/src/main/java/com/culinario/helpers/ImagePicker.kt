package com.culinario.helpers

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.culinario.R

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    onImagePicked: (Bitmap) -> Unit
) {
    val context = LocalContext.current
    val launcherActivity = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri == null) return@rememberLauncherForActivityResult

        onImagePicked(loadAndCompressImage(context, uri)!!)
    }

    IconButton(
        modifier = modifier,
        onClick = {
            launcherActivity.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.image_icon),
            contentDescription = "camera switch"
        )
    }
}
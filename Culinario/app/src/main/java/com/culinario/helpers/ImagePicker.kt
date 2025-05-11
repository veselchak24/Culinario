package com.culinario.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ImagePicker(
    context: Context,
    onImagePicked: (Bitmap) -> Unit
) {
    val launcherActivity = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri == null) return@rememberLauncherForActivityResult

        val inputSteam = context.contentResolver.openInputStream(uri)

        inputSteam?.close()
        onImagePicked(BitmapFactory.decodeStream(inputSteam))
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .scrollable (
                rememberScrollState(),
                Orientation.Vertical
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        launcherActivity.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}
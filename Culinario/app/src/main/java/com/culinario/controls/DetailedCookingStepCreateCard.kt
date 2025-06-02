package com.culinario.controls

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.culinario.R
import com.culinario.helpers.StorageUploader
import com.culinario.mvp.models.DetailedCookingStep
import kotlinx.coroutines.launch

@Composable
fun DetailedCookingStepCreateCard(
    step: DetailedCookingStep,
    storageUploader: StorageUploader,
    onDelete: () -> Unit,
    onUpdate: (DetailedCookingStep) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val isDialogOpen = remember { mutableStateOf(false) }
    val isDropdownMenuOpen = remember { mutableStateOf(false) }

    var imageUrl by remember { mutableStateOf(step.imageUrl) }
    var title by remember { mutableStateOf(step.title) }
    var time by remember { mutableIntStateOf(step.time) }
    var description by remember { mutableStateOf(step.description) }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        uri?.let {
            coroutineScope.launch {
                storageUploader.uploadImage(it) { uploadedImageUrl ->
                    imageUrl = uploadedImageUrl
                }
            }
        }
    }

    if (isDialogOpen.value) {
        Dialog(
            onDismissRequest = {
                closeAndUpdate(
                    onUpdate,
                    imageUrl,
                    title,
                    time,
                    description,
                    isDialogOpen
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainerLow),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                ImagePicker(imageUrl, imagePicker)

                Column(
                    modifier = Modifier
                        .padding(25.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    TextField(
                        value = title,
                        onValueChange = {
                            title = it
                        },
                        label = {
                            Text(
                                text = "Заголовок страницы"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        )
                    )

                    TextField(
                        value = description,
                        onValueChange = {
                            description = it
                        },
                        label = {
                            Text(
                                text = "Описнание страницы"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                    )

                    TextField(
                        value = if (time == 0) "" else time.toString(),
                        onValueChange = {
                            time = it.toIntOrNull() ?: 0
                        },
                        label = {
                            Text(
                                text = "Время (сек.)"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                closeAndUpdate(
                                    onUpdate,
                                    imageUrl,
                                    title,
                                    time,
                                    description,
                                    isDialogOpen
                                )
                            }
                        )
                    )

                    Button(
                        onClick = {
                            closeAndUpdate(
                                onUpdate,
                                imageUrl,
                                title,
                                time,
                                description,
                                isDialogOpen
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("Применить изменения")
                    }
                }
            }
        }
    }

    Box {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .fillMaxWidth()
                .height(100.dp)
                .clickable {
                    isDialogOpen.value = true
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            ) {
                AsyncImage(
                    model = imageUrl.ifEmpty { stringResource(R.string.default_background_image_url) },
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.surfaceContainer
                                )
                            )
                        )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    maxLines = 12,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .basicMarquee()
                )

                Text(
                    text = description,
                    fontSize = 11.sp,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .basicMarquee()
                )
            }
        }

        DetailedCookingStepDropDownMenu(
            isDropdownMenuOpen,
            isDialogOpen
        ) {
            onDelete()
        }
    }
}

private fun closeAndUpdate(
    onUpdate: (DetailedCookingStep) -> Unit,
    imageUrl: String,
    title: String,
    time: Int,
    description: String,
    isDialogOpen: MutableState<Boolean>,
) {
    onUpdate(
        DetailedCookingStep(
            imageUrl = imageUrl,
            title = title,
            time = time,
            description = description,
        )
    )

    isDialogOpen.value = false
}

@Composable
private fun ImagePicker(
    imageUrl: String,
    imagePicker: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
) {
    Box(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = imageUrl.ifEmpty { stringResource(R.string.default_background_image_url) },
            contentDescription = "image url",
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    imagePicker.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                },
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
    }
}

@Composable
private fun BoxScope.DetailedCookingStepDropDownMenu(
    isDropdownMenuOpen: MutableState<Boolean>,
    isDialogOpen: MutableState<Boolean>,
    onDelete: () -> Unit
) {
    Box(
        modifier = Modifier.Companion
            .align(Alignment.CenterEnd)
    ) {
        IconButton(
            onClick = {
                isDropdownMenuOpen.value = true
            }
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options"
            )
        }

        DropdownMenu(
            expanded = isDropdownMenuOpen.value,
            onDismissRequest = {
                isDropdownMenuOpen.value = false
            }
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "edit icon"
                    )
                },
                text = {
                    Text(
                        text = "Изменить",
                    )
                },
                onClick = {
                    isDialogOpen.value = true
                }
            )

            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "edit icon"
                    )
                },
                text = {
                    Text(
                        text = "Удалить",
                    )
                },
                onClick = {
                    onDelete()
                }
            )
        }
    }
}
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.culinario.R
import com.culinario.helpers.NutritionInfoEditItem
import com.culinario.helpers.StorageUploader
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.NutritionInfo
import kotlinx.coroutines.launch

@Composable
fun IngredientCreateCard(
    ingredient: Ingredient,
    storageUploader: StorageUploader,
    onDelete: () -> Unit,
    onUpdate: (Ingredient) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val isDialogOpen = remember { mutableStateOf(false) }
    val isDropdownMenuOpen = remember { mutableStateOf(false) }

    var name by remember { mutableStateOf(ingredient.name) }
    var imageUrl by remember { mutableStateOf(ingredient.imageUrl) }
    var quantity by remember { mutableIntStateOf(ingredient.quantity?.toInt() ?: 0) }
    var unit by remember { mutableStateOf(ingredient.unit) }

    val calories = remember { mutableIntStateOf(ingredient.nutritionInfo.calories.toInt()) }
    val proteins = remember { mutableIntStateOf(ingredient.nutritionInfo.proteins.toInt()) }
    val fats = remember { mutableIntStateOf(ingredient.nutritionInfo.fats.toInt()) }
    val carbohydrates = remember { mutableIntStateOf(ingredient.nutritionInfo.carbohydrates.toInt()) }

    var isOptional by remember { mutableStateOf(ingredient.isOptional) }

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
                    name,
                    imageUrl,
                    quantity,
                    unit,
                    calories,
                    proteins,
                    fats,
                    carbohydrates,
                    isOptional,
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
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = {
                            Text(
                                text = "Название рецепта"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        )
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        TextField(
                            value = if (quantity == 0) "" else quantity.toString(),
                            onValueChange = {
                                quantity = it.toIntOrNull() ?: 0
                            },
                            label = {
                                Text(
                                    text = "Кол-во ингредиента",
                                    maxLines = 1,
                                    modifier = Modifier
                                        .basicMarquee()
                                )
                            },
                            modifier = Modifier
                                .weight(1f),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )

                        TextField(
                            value = unit,
                            onValueChange = {
                                unit = it
                            },
                            label = {
                                Text(
                                    text = "Единицы измерения",
                                    maxLines = 1,
                                    modifier = Modifier
                                        .basicMarquee()
                                )
                            },
                            modifier = Modifier
                                .width(80.dp),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    Spacer(Modifier.height(5.dp))

                    NutritionInfoEditItem(
                        calories = calories,
                        proteins = proteins,
                        fats = fats,
                        carbohydrates = carbohydrates,
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Обязательный ингредиент"
                        )

                        Checkbox(
                            checked = isOptional,
                            onCheckedChange = {
                                isOptional = it
                            }
                        )
                    }

                    Button(
                        onClick = {
                            closeAndUpdate(
                                onUpdate,
                                name,
                                imageUrl,
                                quantity,
                                unit,
                                calories,
                                proteins,
                                fats,
                                carbohydrates,
                                isOptional,
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
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .basicMarquee()
                )

                Text(
                    text = if (quantity == 0) "" else "$quantity $unit",
                    fontSize = 11.sp
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
    onUpdate: (Ingredient) -> Unit,
    name: String,
    imageUrl: String,
    quantity: Int,
    unit: String,
    calories: MutableIntState,
    proteins: MutableIntState,
    fats: MutableIntState,
    carbohydrates: MutableIntState,
    isOptional: Boolean,
    isDialogOpen: MutableState<Boolean>,
) {
    onUpdate(
        Ingredient(
            name = name,
            imageUrl = imageUrl,
            quantity = quantity.toDouble(),
            unit = unit,
            nutritionInfo = NutritionInfo(
                calories = calories.intValue.toDouble(),
                proteins = proteins.intValue.toDouble(),
                fats = fats.intValue.toDouble(),
                carbohydrates = carbohydrates.intValue.toDouble(),
            ),
            isOptional = isOptional
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
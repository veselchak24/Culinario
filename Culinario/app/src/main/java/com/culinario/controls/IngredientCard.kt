package com.culinario.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.culinario.mvp.models.Ingredient
import kotlin.math.roundToInt

@Composable
fun IngredientCard(
    ingredient: Ingredient
) {
    val isDescriptionOpen = remember { mutableStateOf(false) }

        IngredientDescription(isDescriptionOpen, ingredient)

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                isDescriptionOpen.value = true
            }
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
        ) {
            AsyncImage(
                model = ingredient.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "ingredient image",
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 5.dp),
        ) {
            Text(
                text = ingredient!!.name,
                modifier = Modifier
                    .basicMarquee()
                    .fillMaxSize(),
                fontWeight = FontWeight.W700,
                lineHeight = 10.sp,
                maxLines = 1
            )

            Text(
                text = "${ingredient.quantity!!.roundToInt()} ${ingredient.unit}",
                lineHeight = 10.sp
            )
        }
    }
}

@Composable
private fun IngredientDescription(
    isDescriptionOpen: MutableState<Boolean>,
    ingredient: Ingredient,
) {
    if (isDescriptionOpen.value) {
        Dialog(
            onDismissRequest = {
                isDescriptionOpen.value = false
            }
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerLow)
                    .fillMaxWidth()
            ) {
                FadingImage(
                    imageUrl = ingredient.imageUrl,
                    MaterialTheme.colorScheme.surfaceContainerLow
                )

                Column(
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Text(
                        text = ingredient.name,
                        fontWeight = FontWeight.W600,
                        fontSize = 26.sp
                    )

                    Text(
                        text = "${ingredient.quantity!!.roundToInt()} ${ingredient.unit}",
                        lineHeight = 10.sp
                    )

                    Text(
                        text = if (ingredient.isOptional) "Необязательный ингредиент" else "Обязательный ингредиент",
                        lineHeight = 10.sp,
                        color = if (ingredient.isOptional) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.primary
                    )

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )

                    NutritionInfo(ingredient.nutritionInfo)
                }
            }
        }
    }
}

@Composable
private fun FadingImage(
    imageUrl: String,
    containerColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "ingredient image",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            containerColor
                        ),
                        startY = .5f
                    )
                )
        )
    }
}
package com.culinario.controls

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.culinario.viewmodel.RecipeCardViewModel
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeCard(
    modifier: Modifier,
    viewModel: RecipeCardViewModel,
    onClick: () -> Unit
) {
    var recipe by remember { mutableStateOf(viewModel.recipe.value) }
    var owner by remember { mutableStateOf(viewModel.user.value) }

    var isCurrentUser by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.recipe.collect { newState ->
            recipe = newState
        }
    }

    LaunchedEffect(Unit) {
        viewModel.user.collect { newState ->
            owner = newState
        }
    }

    LaunchedEffect(Unit) {
        viewModel.isCurrentUser.collect { newState ->
            isCurrentUser = newState
        }
    }

    var clicked by remember { mutableStateOf(false) }
    LaunchedEffect(clicked) {
        if (clicked) {
            onClick()
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    clicked = true
                }
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(170.dp),
                model = recipe?.recipeImageBackgroundUrl ?: "",
                contentScale = ContentScale.Crop,
                contentDescription = "idk"
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Column {
                    Text(
                        text = recipe?.name ?: "",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        modifier = Modifier
                            .basicMarquee()
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier.fillMaxWidth().wrapContentHeight()
                    ) {
                        AsyncImage(
                            model = owner.imageUrl,
                            contentDescription = "author",
                            Modifier
                                .size(20.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            modifier = Modifier.padding(start = 3.dp),
                            text = owner.name,
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 1
                        )

                        if (isCurrentUser) {
                            Text(
                                text = "мой рецепт",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(7.dp))
                                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                                    .padding(horizontal = 4.dp)
                            )
                        }
                    }
                }

                RecipeCardDropDownMenu(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun ShimmerRecipeCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shimmer()
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .height(250.dp)
            .width(200.dp)
    )
}

@Composable
fun RecipeCardDropDownMenu(
    modifier: Modifier,
    viewModel: RecipeCardViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var isCurrentUser by remember { mutableStateOf(false) }
    var isRecipeLiked by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.isCurrentUser.collect {
            isCurrentUser = it
        }
    }

    LaunchedEffect(Unit) {
        viewModel.isRecipeLiked.collect {
            isRecipeLiked = it
        }
    }

    Box(
        modifier = modifier
    ) {
        IconButton(
            onClick = {
                expanded = !expanded
            }
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = if (isRecipeLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite icon"
                    )
                },
                text = {
                    Text("Нравится")
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.toggleLike {
                            isRecipeLiked = it
                            Toast.makeText(context, if (isRecipeLiked) "Добавлено в понравившиеся рецепты" else "Убрано из понравившихся рецептов", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            )

            if (isCurrentUser) {
                HorizontalDivider()

                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "edit icon"
                        )
                    },
                    text = {
                        Text("Изменить")
                    },
                    onClick = { }
                )

                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "edit icon"
                        )
                    },
                    text = {
                        Text("Удалить")
                    },
                    onClick = {
                        viewModel.deleteSelfFromDatabase {
                            Toast.makeText(context, "Рецепт успешно удалён", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }
}
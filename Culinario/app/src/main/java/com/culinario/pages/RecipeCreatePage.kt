package com.culinario.pages

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.culinario.R
import com.culinario.controls.DetailedCookingStepCreateCard
import com.culinario.controls.DetailedStepPageCard
import com.culinario.controls.EnumButton
import com.culinario.controls.IngredientCreateCard
import com.culinario.helpers.NutritionInfoEditItem
import com.culinario.helpers.StorageUploader
import com.culinario.helpers.asWord
import com.culinario.mvp.models.NutritionInfo
import com.culinario.mvp.models.DetailedCookingStep
import com.culinario.mvp.models.Difficulty
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.OtherInfo
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeType
import com.culinario.viewmodel.RecipeCreatePageViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random

@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Deprecated("old create recipe page")
fun RecipeCreatePage(
    modifier: Modifier = Modifier,
    viewModel: RecipeCreatePageViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    val storageUploader by lazy { StorageUploader(context) }

    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val totalWeight = remember { mutableIntStateOf(0) }
    val cookingSpeed = remember { mutableIntStateOf(0) }
    val recipeType = remember { mutableStateOf(RecipeType.QUICK) }
    val difficulty = remember { mutableStateOf(Difficulty.EASY) }
    val nutritionInfo = remember { mutableStateOf(NutritionInfo()) }

    val ingredients = SnapshotStateList<Ingredient>()

    val detailedSteps = SnapshotStateList<DetailedCookingStep>()

    val recipeImagesUrl = SnapshotStateList<String>()

    val coroutineScope = rememberCoroutineScope()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior (
        rememberTopAppBarState()
    )

    val pageModifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 30.dp)

    val pagerState = rememberPagerState (
        initialPage = 0,
        pageCount = { 4 }
    )

    var navigateBack by remember { mutableStateOf(false) }
    var recipeLoading by remember { mutableStateOf(false) }

    LaunchedEffect(navigateBack) {
        if (navigateBack) {
            navController.popBackStack()
        }
    }

    BackHandler {
        if (pagerState.currentPage > 0) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        } else {
            navigateBack = true
        }
    }

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar() }
    ) { padding ->
        Column {
            HorizontalPager(
                state = pagerState,
                Modifier
                    .padding(padding)
                    .fillMaxWidth()
                    .fillMaxHeight(.85f),
                beyondViewportPageCount = 4
            ) {
                when (it) {
                    0 -> BasicInfoCreatePage(
                        pageModifier,
                        name,
                        description,
                        difficulty,
                        recipeType,
                        totalWeight,
                        cookingSpeed,
                        nutritionInfo,
                        onDone = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    )
                    1 -> IngredientCreatePage(
                        pageModifier,
                        ingredients,
                        storageUploader
                    )
                    2 -> DetailedStepCreatePage(
                        pageModifier,
                        detailedSteps,
                        storageUploader
                    )
                    3 -> ImagesPage(
                        pageModifier,
                        recipeImagesUrl,
                        storageUploader
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(15.dp)
            ) {
                if (pagerState.currentPage == 3) {
                    Button (
                        onClick = {
                            viewModel.updateRecipe {
                                Recipe(
                                    name = name.value,
                                    description = description.value,
                                    recipeImagesUrl = recipeImagesUrl,
                                    commentaries = listOf(),
                                    cookingSpeed = cookingSpeed.intValue,
                                    ingredients = ingredients,
                                    totalWeight = totalWeight.intValue,
                                    steps = detailedSteps,
                                    recipeType = recipeType.value,
                                    difficulty = difficulty.value,
                                    otherInfo = OtherInfo(),
                                    nutritionInfo = nutritionInfo.value
                                )
                            }

                            recipeLoading = true

                            viewModel.uploadRecipe {
                                recipeLoading = false
                                navigateBack = true
                            }
                        },
                        enabled = !recipeLoading
                    ) {
                        Text(text = stringResource(R.string.create_text))
                    }
                } else {
                    Button (
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    ) {
                        Text(stringResource(R.string.next_step_text))
                    }
                }
            }
        }
    }
}

@Composable
fun BasicInfoCreatePage(
    modifier: Modifier = Modifier,
    name: MutableState<String>,
    description: MutableState<String>,
    difficulty: MutableState<Difficulty>,
    recipeType: MutableState<RecipeType>,
    totalWeight: MutableState<Int>,
    cookingSpeed: MutableState<Int>,
    nutritionInfo: MutableState<NutritionInfo>,
    onDone: () -> Unit
) {
    val calories = remember { mutableIntStateOf(0) }
    val proteins = remember { mutableIntStateOf(0) }
    val fats = remember { mutableIntStateOf(0) }
    val carbohydrates = remember { mutableIntStateOf(0) }

    LaunchedEffect(calories, proteins, fats, carbohydrates) {
        nutritionInfo.value = NutritionInfo(
            calories.intValue.toDouble(),
            proteins.intValue.toDouble(),
            fats.intValue.toDouble(),
            carbohydrates.intValue.toDouble()
        )
    }

    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField (
            modifier = Modifier
                .fillMaxWidth(),
            value = name.value,
            onValueChange = {
                name.value = it

            },
            label = {
                Text(stringResource(R.string.recipe_name_text))
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        TextField (
            modifier = Modifier
                .fillMaxWidth(),
            value = description.value,
            onValueChange = {
                description.value = it
            },
            label = {
                Text(stringResource(R.string.recipe_desciption_text))
            }
        )

        Spacer(Modifier.height(5.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            EnumButton(
                modifier = Modifier
                    .weight(1f),
                enum = difficulty.value,
                label = "Сложность рецепта",
                enumConverter = {
                    it.asWord()
                }
            ) {
                difficulty.value = it
            }

            EnumButton(
                modifier = Modifier
                    .weight(1f),
                enum = recipeType.value,
                label = "Тип рецепта",
                enumConverter = {
                    it.asWord()
                }
            ) {
                recipeType.value = it
            }
        }

        Spacer(Modifier.height(5.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = if (totalWeight.value == 0) "" else totalWeight.value.toString(),
            onValueChange = {
                totalWeight.value = it.toIntOrNull() ?: 0
            },
            label = {
                Text("Общий вес продукта")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next
            )
        )

        Spacer(Modifier.height(5.dp))

        NutritionInfoEditItem(
            calories = calories,
            proteins = proteins,
            fats = fats,
            carbohydrates = carbohydrates,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = if (cookingSpeed.value == 0) "" else cookingSpeed.value.toString(),
            onValueChange = {
                cookingSpeed.value = it.toIntOrNull() ?: 0
            },
            label = {
                Text("Скорость приготовления (сек.)")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDone()
                }
            )
        )
    }
}

@Composable
private fun IngredientCreatePage(
    modifier: Modifier = Modifier,
    ingredients: SnapshotStateList<Ingredient>,
    storageUploader: StorageUploader
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            itemsIndexed(
                items = ingredients,
                key = { index, ingredient -> ingredient.hashCode() + index }
            ) { index, ingredient ->
                IngredientCreateCard(
                    ingredient = ingredient,
                    storageUploader = storageUploader,
                    onDelete = {
                        ingredients.remove(ingredient)
                    }
                ) {
                    ingredients[index] = it
                    println(ingredients[index])
                }
            }
        }

        Spacer(Modifier.height(5.dp))

        Button(
            onClick = {
                ingredients.add(Ingredient("Ингредиент"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Добавить ингредиент"
            )
        }
    }
}

@Composable
private fun DetailedStepCreatePage(
    modifier: Modifier = Modifier,
    steps: SnapshotStateList<DetailedCookingStep>,
    storageUploader: StorageUploader
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            itemsIndexed(
                items = steps,
                key = { index, step -> step.hashCode() + index }
            ) { index, step ->
                DetailedCookingStepCreateCard(
                    step = step,
                    storageUploader = storageUploader,
                    onDelete = {
                        steps.remove(step)
                    }
                ) {
                    steps[index] = it
                    println(steps[index])
                }
            }
        }

        Spacer(Modifier.height(5.dp))

        Button(
            onClick = {
                steps.add(DetailedCookingStep(title = "Новый шаг"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Добавить шаг приготовления"
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImagesPage(
    modifier: Modifier = Modifier,
    recipeImagesUrl: SnapshotStateList<String>,
    storageUploader: StorageUploader
) {
    val coroutineScope = rememberCoroutineScope()
    val haptics = LocalHapticFeedback.current

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) {
        it.forEach { uri ->
            coroutineScope.launch {
                storageUploader.uploadImage(uri) { loadImageUrl ->
                    recipeImagesUrl.add(loadImageUrl)
                }
            }
        }
    }

    Column (
        modifier = modifier
            .fillMaxSize()
            .scrollable(
                rememberScrollState(),
                Orientation.Vertical
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(
                items = recipeImagesUrl,
                key = { it.hashCode() }
            ) { url ->
                var isOpen by rememberSaveable { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {
                    AsyncImage(
                        model = url,
                        contentDescription = "picked image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .combinedClickable(
                                onClick = { },
                                onLongClick = {
                                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                    isOpen = true
                                },
                                onLongClickLabel = "Открытие контекстного меню"
                            )
                    )

                    DropdownMenu(
                        expanded = isOpen,
                        onDismissRequest = {
                            isOpen = false
                        }
                    ) {
                        DropdownMenuItem(
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Корзина"
                                )
                            },
                            text = {
                                Text(
                                    text = "Удалить"
                                )
                            },
                            onClick = {
                                recipeImagesUrl.remove(url)
                            }
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                imagePicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Выбать картинки"
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar() {
    LargeTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            Text(
                text = stringResource(R.string.recipe_create_header),
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 1
            )
        }
    )
}
package com.culinario.pages

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.culinario.R
import com.culinario.controls.Header
import com.culinario.helpers.RecipeSaveHelper
import com.culinario.helpers.loadAndCompressImage
import com.culinario.mvp.models.Difficulty
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.OtherInfo
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeType
import com.culinario.mvp.models.Unit
import com.culinario.viewmodel.RecipeCreatePageViewModel
import com.google.firebase.annotations.concurrent.Background
import kotlinx.coroutines.launch
import kotlin.random.Random

@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeCreatePage(
    modifier: Modifier = Modifier,
    viewModel: RecipeCreatePageViewModel,
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior (
        rememberTopAppBarState()
    )

    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    val recipeName = remember { mutableStateOf("") }
    val recipeDescription = remember { mutableStateOf("") }

    val ingredients = remember { mutableStateOf("") }
    val steps = remember { mutableStateOf("") }

    val titleBitmap: MutableState<Bitmap> = remember { mutableStateOf(BitmapFactory.decodeStream(context.resources.openRawResource(R.drawable.user_background_placeholder))) }
    val picturesBitmap = remember { mutableStateOf(listOf<Bitmap>()) }

    var backgroundImageUrl by remember { mutableStateOf("") }

    val recipeTitleImageLauncher = pickVisualResource {
        coroutineScope.launch {
            viewModel.uploadSampleFile(it!!) { url ->
                backgroundImageUrl = url
                Toast.makeText(context, url, Toast.LENGTH_SHORT).show()
            }
        }

        titleBitmap.value = loadAndCompressImage(context, it)!!
    }

    val picturesImageLauncher = pickVisualResource {
        val temp = picturesBitmap.value.toMutableList()
        temp.add(loadAndCompressImage(context, it)!!)

        picturesBitmap.value = temp.toList()
    }

    val pagerState = rememberPagerState (
        initialPage = 0,
        pageCount = { 3 }
    )

    var navigateBack by remember { mutableStateOf(false) }

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
        topBar = {
            TopBar(scrollBehavior)
        }
    ) { padding ->
        Column {
            HorizontalPager (
                state = pagerState,
                Modifier
                    .padding(padding)
                    .fillMaxWidth()
                    .fillMaxHeight(.8f)
            ) { page ->
                when (page) {
                    0 -> {
                        Box (
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 30.dp)
                        ) {
                            BasicInfoPage (
                                recipeName,
                                recipeDescription
                            )
                        }
                    }
                    1 -> {
                        Box (
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 30.dp)
                        ) {
                            StepsAndIngredientsPage(
                                ingredients,
                                steps
                            )
                        }
                    }
                    2 -> {
                        Box (
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize()
                                .padding(horizontal = 30.dp)
                        ) {
                            ImagesPage (
                                recipeTitleImageLauncher = recipeTitleImageLauncher,
                                titleBitmap = titleBitmap,
                                picturesBitmap = picturesBitmap,
                                picturesImageLauncher = picturesImageLauncher
                            )

                        }
                    }
                    else -> Box (
                        Modifier.fillMaxSize()
                    )
                }
            }


            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(15.dp)
            ) {
                if (pagerState.currentPage == 2) {
                    Button (
                        onClick = {
                            saveRecipe(
                                recipeName.value,
                                recipeDescription.value,
                                ingredients.value,
                                steps.value,
                                backgroundImageUrl,
                                listOf(),
                                viewModel,
                            )

                            navigateBack = true
                        }
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
fun BasicInfoPage (
    recipeName: MutableState<String>,
    recipeDescription: MutableState<String>
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField (
            modifier = Modifier
                .fillMaxWidth(),
            value = recipeName.value,
            onValueChange = {
                recipeName.value = it
            },
            label = {
                Text(stringResource(R.string.recipe_name_text))
            },
            maxLines = 1
        )

        TextField (
            modifier = Modifier
                .fillMaxWidth(),
            value = recipeDescription.value,
            onValueChange = {
                recipeDescription.value = it
            },
            label = {
                Text(stringResource(R.string.recipe_desciption_text))
            }
        )
    }
}

@Composable
private fun StepsAndIngredientsPage(
    ingredients: MutableState<String>,
    steps: MutableState<String>
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField (
            modifier = Modifier
                .fillMaxWidth(),
            value = ingredients.value,
            onValueChange = {
                ingredients.value = it
            },
            placeholder = {
                Text(stringResource(R.string.ingredients_steps_text_field_placeholder_text))
            },
            label = {
                Text(stringResource(R.string.ingredients_text))
            }
        )

        TextField (
            modifier = Modifier
                .fillMaxWidth(),
            value = steps.value,
            onValueChange = {
                steps.value = it
            },
            placeholder = {
                Text(stringResource(R.string.cooking_steps_text_field_placeholder_text))
            },
            label = {
                Text(stringResource(R.string.cooking_steps_text))
            }
        )
    }
}

@Composable
private fun ImagesPage(
    recipeTitleImageLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    titleBitmap: MutableState<Bitmap>,
    picturesBitmap: MutableState<List<Bitmap>>,
    picturesImageLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                rememberScrollState(),
                Orientation.Vertical
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BackgroundImageDrawer(recipeTitleImageLauncher, titleBitmap)

        OtherImages(picturesBitmap, picturesImageLauncher)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar (
    scrollBehavior: TopAppBarScrollBehavior
) {
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
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun OtherImages (
    picturesBitmap: MutableState<List<Bitmap>>,
    picturesImageLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>
) {
    Column {
        Header(stringResource(R.string.other_images_text))

        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            for (bitmap in picturesBitmap.value)
                ImageDrawer(bitmap)
        }
    }

    Button (
        onClick = {
            picturesImageLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        },
        content = {
            Text (
                text = stringResource(R.string.add_image_text)
            )
        }
    )
}

@Composable
fun pickVisualResource (
    onUriReceive: (uri: Uri?) -> kotlin.Unit
): ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?> {
    return rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri == null) return@rememberLauncherForActivityResult

        onUriReceive(uri)
    }
}

@Composable
private fun BackgroundImageDrawer (
    launcher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    bitmap: MutableState<Bitmap>
) {
    Column {
        Header(stringResource(R.string.recipe_bg_image_header_text))
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    bitmap = bitmap.value.asImageBitmap(),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
private fun ImageDrawer (
    bitmap: Bitmap
) {
    OutlinedCard (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            bitmap = bitmap.asImageBitmap(),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
    }
}

private fun saveRecipe (
    recipeName: String,
    recipeDescription: String,
    ingredients: String,
    steps: String,
    backgroundImageUrl: String,
    recipeImagesUrl: List<String>,
    viewModel: RecipeCreatePageViewModel
) {
    println(backgroundImageUrl)

    val recipe = Recipe(
        id = Random.nextInt(1000000, 9999999).toString(),
        name = recipeName,
        description = recipeDescription,
        recipeImageBackgroundUrl = backgroundImageUrl,
        recipeImagesUrl = recipeImagesUrl,
        ingredients = ingredients
            .split('\n')
            .map { x -> Ingredient(x, 1.0, Unit.CUPS) }
            .toList(),
        steps = steps.split('\n').map { x -> x.trim() },
        recipeType = RecipeType.QUICK,
        cookingSpeed = 100,
        difficulty = Difficulty.MEDIUM,
        otherInfo = OtherInfo(0, 0)
    )

    viewModel.addRecipe(recipe)
}
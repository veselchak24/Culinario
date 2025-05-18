package com.culinario.pages

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.culinario.mvp.models.RecipeImageResources
import com.culinario.mvp.models.RecipeType
import com.culinario.mvp.models.Unit
import com.culinario.mvp.models.repository.recipe.RecipeRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeCreatePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    userId: String,
    recipeRepository: RecipeRepository
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

    val recipeTitleImageLauncher = pickVisualResource {
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

                            Button (
                                modifier = Modifier
                                    .align(Alignment.BottomEnd),
                                onClick = {
                                    saveRecipe (
                                        recipeName.value,
                                        recipeDescription.value,
                                        ingredients.value,
                                        steps.value,
                                        userId,
                                        recipeRepository,
                                        navController,
                                        titleBitmap.value,
                                        picturesBitmap.value,
                                        context
                                    )
                                }
                            ) {
                                Text(text = "Create")
                            }
                        }
                    }
                    else -> Box (
                        Modifier.fillMaxSize()
                    )
                }
            }

            BackHandler {
                if (pagerState.currentPage > 0) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                } else {
                    navController.navigate("UserPage/$userId")
                }
            }

            Button (
                modifier = Modifier
                    .align(Alignment.End),
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            ) {
                Text("Next")
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
                Text("Recipe name")
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
                Text("Recipe description")
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
                Text("Every ingredient on new line")
            },
            label = {
                Text("Ingredients")
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
                Text("Every step on new line")
            },
            label = {
                Text("Steps")
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
            .scrollable (
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
        Header("Other images")

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
                text = "Add image"
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
        Header("Recipe title image")
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
    userId: String,
    recipeRepository: RecipeRepository,
    navController: NavController,
    headerImage: Bitmap,
    listImageResources: List<Bitmap>,
    context: Context
) {
    val saveHelper = RecipeSaveHelper(context)

    val recipe = Recipe(
        id = Random.nextInt(1000000, 9999999).toString(),
        userId = userId,
        name = recipeName,
        description = recipeDescription,
        recipeImageResources = RecipeImageResources (
            recipeBackgroundImageUri = saveHelper.saveBitmapToFile(headerImage),
            recipePicturesUri = listImageResources.map {
                saveHelper.saveBitmapToFile(it)
            }.toTypedArray()
        ),
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

    recipeRepository.addRecipe(recipe)
    recipeRepository.commit()

    navController.popBackStack()
}
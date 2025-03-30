package com.culinario.pages

import android.R.attr.bitmap
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Context.MODE_WORLD_WRITEABLE
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.culinario.R
import com.culinario.backend.RECIPE_JSON_FILE_NAME
import com.culinario.controls.Header
import com.culinario.helpers.RecipeSaveHelper
import com.culinario.mvp.models.Difficulty
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.OtherInfo
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeImageResources
import com.culinario.mvp.models.RecipeType
import com.culinario.mvp.models.Unit
import com.culinario.mvp.models.repository.recipe.RecipeRepository
import java.io.File
import java.io.OutputStream
import kotlin.random.Random


@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRecipePage(modifier: Modifier = Modifier, navController: NavController, userId: String, recipeRepository: RecipeRepository) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )

    val context = LocalContext.current
    val debugText = remember { mutableStateOf("") }
    var recipeName by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }

    var titleUri: Uri by remember { mutableStateOf(Uri.parse("")) }
    var picturesUri: MutableList<Uri> by remember { mutableStateOf(mutableListOf())  }

    val titleBitmap: MutableState<Bitmap> = remember { mutableStateOf(BitmapFactory.decodeStream(context.resources.openRawResource(R.drawable.user_background_placeholder))) }
    val picturesBitmap: MutableState<MutableList<Bitmap>> = remember { mutableStateOf(mutableListOf(BitmapFactory.decodeStream(context.resources.openRawResource(R.drawable.user_background_placeholder)))) }

    val recipeTitleImageLauncher = pickVisualResource {
        titleUri = it!!

        val inputSteam = context.contentResolver.openInputStream(it)

        titleBitmap.value = BitmapFactory.decodeStream(inputSteam)
        inputSteam?.close()
    }

    val picturesImageLauncher = pickVisualResource {
        picturesUri.add(it!!)

        val inputSteam = context.contentResolver.openInputStream(it)

        picturesBitmap.value.add(BitmapFactory.decodeStream(inputSteam))
        inputSteam?.close()
    }

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(scrollBehavior)
        }
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(horizontal = 30.dp)
                .padding(vertical = 50.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackgroundImageDrawer(recipeTitleImageLauncher, titleBitmap)

            OtherImages(picturesBitmap, picturesImageLauncher)

            TextField (
                value = recipeName,
                label = { Text("Имя рецепта") },
                onValueChange = { recipeName = it }
            )

            Text (
                text = "Приготовление",
                textAlign = TextAlign.Left
            )

            TextField (
                value = ingredients,
                label = { Text("Ингредиенты") },
                onValueChange = { ingredients = it }
            )

            TextField (
                value = steps,
                label = { Text("Шаги") },
                onValueChange = { steps = it }
            )

            Button(onClick = {
                saveRecipe(
                    recipeName,
                    ingredients,
                    steps,
                    userId,
                    recipeRepository,
                    navController,
                    titleBitmap.value,
                    picturesBitmap.value.toList(),
                    context
                )
            }) {
                Text(text = "Save data")
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(scrollBehavior: TopAppBarScrollBehavior) {
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
private fun OtherImages(
    picturesBitmap: MutableState<MutableList<Bitmap>>,
    picturesImageLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Header("Other images")

        for (bitmap in picturesBitmap.value) {
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
private fun pickVisualResource (onUriReceive: (uri: Uri?) -> kotlin.Unit): ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?> {
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
    OutlinedCard(
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

private fun saveRecipe(
    recipeName: String,
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
        description = "empty",
        recipeImageResources = RecipeImageResources (
            recipeBackgroundImageUri = saveHelper.saveBitmapToFile(headerImage),
            recipePicturesUri = listImageResources.map {
                saveHelper.saveBitmapToFile(it)
            }.toTypedArray()
        ),
        ingredients = ingredients
            .split(",")
            .map { x -> Ingredient(x, 1.0, Unit.CUPS) }
            .toList(),
        steps = steps.split(",").map { x -> x.trim() },
        recipeType = RecipeType.QUICK,
        cookingSpeed = 100,
        difficulty = Difficulty.MEDIUM,
        otherInfo = OtherInfo(100, 20)
    )

    recipeRepository.addRecipe(recipe)
    recipeRepository.commit()

    navController.popBackStack()
}
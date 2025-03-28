package com.culinario.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.culinario.R
import com.culinario.backend.LocalRecipesHandler
import com.culinario.mvp.models.Author
import com.culinario.mvp.models.Difficulty
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeType
import com.culinario.mvp.models.Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SerializationDemoPage(modifier: Modifier = Modifier, navController: NavController? = null) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )

    val context = LocalContext.current
    val debugText = remember { mutableStateOf("") }

    var recipeName by remember { mutableStateOf("") }
    var authorName by remember { mutableStateOf("") }
    var authorEmail by remember { mutableStateOf("") }

    var ingredients by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }

    Scaffold (
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
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
    ) { padding ->
        Column (
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(vertical = 50.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = recipeName,
                label = { Text("Имя рецепта") },
                onValueChange = { recipeName = it }
            )

            Text(
                text = "Автор",
                textAlign = TextAlign.Left
            )

            TextField(
                value = authorName,
                label = { Text("Имя") },
                onValueChange = { authorName = it }
            )

            TextField(
                value = authorEmail,
                label = { Text("E-mail") },
                onValueChange = { authorEmail = it }
            )

            Text(
                text = "Приготовление",
                textAlign = TextAlign.Left
            )

            TextField(
                value = ingredients,
                label = { Text("Ингредиенты") },
                onValueChange = { ingredients = it }
            )

            TextField(
                value = steps,
                label = { Text("Шаги") },
                onValueChange = { steps = it }
            )

            Button(onClick = {
                debugText.value = "объект сохранён"

                val author = Author(
                    name = authorName,
                    email = authorEmail)

                val recipe = Recipe(
                    id = "11",
                    name = recipeName,
                    description = "empty",
                    imageUrl = "https://google.com",
                    author = author,
                    ingredients = ingredients
                        .split(",")
                        .map { x -> Ingredient(x, 1.0, Unit.CUPS) }
                        .toList(),
                    steps = steps.split(",").map { x -> x.trim() },
                    recipeType = RecipeType.QUICK,
                    otherCharacteristics = emptyMap(),
                    cookingSpeed = 100,
                    difficulty = Difficulty.MEDIUM
                )

                LocalRecipesHandler.AddRecipe(recipe, context)
            }) {
                Text(text = "Save data")
            }

            Text(
                text = debugText.value,
                modifier = modifier
                    .wrapContentSize()
                    .padding(horizontal = 25.dp)
            )
        }
    }
}
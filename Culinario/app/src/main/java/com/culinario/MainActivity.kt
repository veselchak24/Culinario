package com.culinario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culinario.backend.LocalRecipeSaverLoader
import com.culinario.backend.RecipeSaverLoader
import com.culinario.mvp.models.Author
import com.culinario.mvp.models.Ingredient
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.RecipeType
import com.culinario.mvp.models.Unit
import com.culinario.ui.theme.CulinarioTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CulinarioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainPage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val debugText = remember { mutableStateOf("") }

    var recipeName by remember { mutableStateOf("") }
    var authorName by remember { mutableStateOf("") }
    var authorEmail by remember { mutableStateOf("") }

    var ingredients by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }


    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 150.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Рецепт",
            fontWeight = FontWeight.Black,
            fontSize = 40.sp,
            modifier = modifier
                .width(150.dp)
        )

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
                otherCharacteristics = emptyMap()
            )
            RecipeSaverLoader().saveRecipe(recipe, LocalRecipeSaverLoader(), context)
        }) {
            Text(text = "Save data")
        }

        Button(onClick = {
            debugText.value = Gson().toJson(RecipeSaverLoader().loadRecipe("test.json", LocalRecipeSaverLoader(), context));
        }) {

            Text(text = "Restore data")
        }

        Text(
            text = debugText.value,
            modifier = modifier
                .wrapContentSize()
                .padding(horizontal = 25.dp)
        )
    }
}
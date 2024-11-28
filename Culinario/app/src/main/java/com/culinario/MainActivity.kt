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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culinario.backend.LocalRecipeSaverLoader
import com.culinario.backend.RecipeSaverLoader
import com.culinario.mvp.models.Recipe
import com.culinario.ui.theme.CulinarioTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val saver = RecipeSaverLoader();
        val recipe = Recipe("Pasta", 777, 123, 0);

        val recipeJson = Gson().toJson(recipe)

        setContent {
            CulinarioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        saver = saver,
                        recipe = recipe,
                        jsonString = recipeJson,
                        text = recipe.toString(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(saver: RecipeSaverLoader, recipe: Recipe, jsonString: String, text: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val debugText = remember { mutableStateOf("") }

    var recipeName by remember { mutableStateOf("") }
    var recipeLikes by remember { mutableStateOf("") }
    var recipeComments by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .fillMaxSize(),
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
        Text(
            text = "Демо",
        )

        TextField(
            value = recipeName,
            label = { Text("Имя рецепта") },
            onValueChange = { recipeName = it }
        )

        TextField(
            value = recipeLikes,
            label = { Text("Кол-во лайков") },
            onValueChange = { recipeLikes = it }
        )

        TextField(
            value = recipeComments,
            label = { Text("Кол-во комментов") },
            onValueChange = { recipeComments = it }
        )

        Text(
            text = debugText.value,
            modifier = modifier
                .wrapContentSize()
                .padding(horizontal = 25.dp)
        )

        Button(onClick = {
            debugText.value = "объект сохранён"
            saver.saveRecipe(Recipe(recipeName, recipeLikes.toInt(), recipeComments.toInt(), 228), LocalRecipeSaverLoader(), context)
        }) {
            Text(text = "Save data")
        }

        Button(onClick = {
            debugText.value = Gson().toJson(saver.loadRecipe("test.json", LocalRecipeSaverLoader(), context));
        }) {

            Text(text = "Restore data")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CulinarioTheme { }
}
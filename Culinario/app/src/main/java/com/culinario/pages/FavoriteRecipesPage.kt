package com.culinario.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.culinario.R
import com.culinario.backend.LocalRecipesHandler
import com.culinario.controls.RecipeCard
import com.culinario.mvp.models.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteRecipesPage() {
    LocalRecipesHandler.UpdateLocalRecipes(LocalContext.current)

    // Состояние для хранения текста поиска
    var textValue by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            // Поисковая строка с отступом сверху
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 55.dp) // Отступ сверху
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp), // Горизонтальный отступ
                    value = textValue, // Текущее значение поиска
                    onValueChange = { newValue ->
                        println("Введенный текст: $newValue") // Логирование
                        textValue = newValue
                    },
                    label = {
                        Text("Поиск рецептов") // Заголовок TextField
                    },
                    placeholder = {
                        Text("Введите название рецепта") // Подсказка
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            val recipes = LocalRecipesHandler.GetLocalRecipes(LocalContext.current)
            // Фильтрация рецептов по поисковому запросу
            val filteredRecipes = recipes.filter { recipe ->
                recipe.name.contains(textValue, ignoreCase = true) // Поиск по названию рецепта
            }

            // Отображение списка рецептов или пустой страницы
            if (filteredRecipes.isNotEmpty()) {
                GridOfFavorite(filteredRecipes, Modifier.padding(top = 8.dp))
            } else {
                EmptyPage()
            }
        }
    }
}

@Composable
fun EmptyPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_heart_broken_24),
                contentDescription = "",
                modifier = Modifier
                    .size(150.dp),
                tint = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = "Здесь пока ничего нет(",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun GridOfFavorite(recipes: List<Recipe>, modifier: Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 75.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(recipes.size) { index ->
            RecipeCard(recipes[index], Modifier.padding(5.dp))
        }
    }
}
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
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.culinario.R
import com.culinario.controls.RecipeCard
import com.culinario.mvp.models.Recipe
import com.culinario.viewmodel.RecipeCardViewModel

@Composable
fun FavoriteRecipesPage(
    modifier: Modifier,
    navController: NavController
) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold (
        modifier = modifier.fillMaxSize(),
        topBar = {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text(stringResource(R.string.enter_recipe_name_label)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 20.dp)
            )
        }
    ) { innerPadding ->
        EmptyPage()

        //TODO: сделать viewModel для FavoriteRecipePage
//        Column(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//        ) {
//            val filteredRecipes = recipes.filter { recipe ->
//                recipe.name.contains(searchQuery, ignoreCase = true)
//            }
//
//            if (filteredRecipes.isNotEmpty()) {
//                GridOfFavorite(filteredRecipes, Modifier.padding(top = 8.dp), navController)
//            } else {
//                EmptyPage()
//            }
//        }
    }
}

@Preview
@Composable
fun EmptyPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.broken_heart_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp),
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
fun GridOfFavorite(recipes: List<Recipe>, modifier: Modifier, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(recipes) { recipe ->
            RecipeCard(Modifier.padding(5.dp), RecipeCardViewModel(recipe.id)) {
                navController.navigate("RecipePage/${recipe.id}")
            }
        }
    }
}
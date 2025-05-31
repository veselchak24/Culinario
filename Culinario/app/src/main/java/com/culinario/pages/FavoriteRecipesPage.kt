package com.culinario.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.culinario.R
import com.culinario.controls.RecipeCard
import com.culinario.mvp.models.User
import com.culinario.viewmodel.FavoritePageViewModel
import com.culinario.viewmodel.RecipeCardViewModel

@Composable
fun FavoriteRecipesPage(
    modifier: Modifier,
    viewModel: FavoritePageViewModel,
    navController: NavController
) {
    var user by remember { mutableStateOf(User()) }

    LaunchedEffect(Unit) {
        viewModel.currentUser.collect {
            user = it
        }
    }

    var searchQuery by remember { mutableStateOf("") }

    Scaffold (
        modifier = modifier,
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
        if (user.likedRecipesId.isNotEmpty()) {
            GridOfFavorite(
                user.likedRecipesId,
                Modifier.padding(innerPadding),
                navController
            )
        } else {
            EmptyPage()
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
fun GridOfFavorite(
    recipesId: List<String>,
    modifier: Modifier,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(
            items = recipesId,
            key = { it }
        ) { recipeId ->
            RecipeCard(Modifier.padding(5.dp), RecipeCardViewModel(recipeId)) {
                navController.navigate("RecipePage/${recipeId}")
            }
        }
    }
}
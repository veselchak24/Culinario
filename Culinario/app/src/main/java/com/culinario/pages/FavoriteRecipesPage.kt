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
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.culinario.R
import com.culinario.backend.LocalRecipesHandler
import com.culinario.controls.RecipeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteRecipesPage() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold (
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Сохранённые",
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        if (LocalRecipesHandler.GetLocalRecipesCount() > 0) {
            GridOfFavorite(Modifier.padding(innerPadding))
        }
        else {
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
        Column (
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
fun GridOfFavorite(modifier: Modifier) {
    LazyVerticalGrid (
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 75.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(LocalRecipesHandler.GetLocalRecipesCount()) {
            LocalRecipesHandler.GetLocalRecipes(LocalContext.current).forEach {
                    item -> RecipeCard(item, Modifier.padding(5.dp))
            }
        }
    }
}

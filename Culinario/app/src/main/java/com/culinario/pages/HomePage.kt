package com.culinario.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.culinario.controls.Header
import com.culinario.controls.RecipeCard
import com.culinario.controls.ShimmerRecipeCard
import com.culinario.mvp.models.Recipe
import com.culinario.viewmodel.HomePageViewModel
import com.culinario.viewmodel.RecipeCardViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    viewModel: HomePageViewModel,
    navController: NavController
) {
    var recipes by remember { mutableStateOf(listOf<Recipe>()) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(Unit) {
        recipes = viewModel.getAllRecipes()
    }

    Scaffold (
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar (
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text (
                       text = "Главная"
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    bottom = 80.dp
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column {
                Header("Лучшие рецепты")
                BestRecipes(recipes, navController)
            }

            Column {
                Header("Все рецепты")
                AllRecipes(
                    recipes,
                    Modifier,
                    navController
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BestRecipes(
    recipes: List<Recipe>,
    navController: NavController
) {
    val selectedRecipes = recipes.take(4)

    val carouselState = rememberCarouselState(
        initialItem = 0,
        itemCount = { selectedRecipes.count() }
    )

    HorizontalMultiBrowseCarousel (
        state = carouselState,
        preferredItemWidth = 300.dp,
        itemSpacing = 5.dp
    ) { index ->
        Box (
            modifier = Modifier
                .maskClip(RoundedCornerShape(16.dp))
                .width(300.dp)
                .height(200.dp)
                .fillMaxSize()
                .clickable {
                    navController.navigate("RecipePage/${selectedRecipes[index].id}")
                }
        )  {
            AsyncImage(
                model = selectedRecipes[index].recipeImageBackgroundUrl,
                contentDescription = "bgImage",
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = selectedRecipes[index].name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .align(Alignment.BottomStart),
                color = Color.White
            )
        }
    }
}

@Composable
fun AllRecipes(
    recipes: List<Recipe>,
    modifier: Modifier,
    navController: NavController,
) {
    Column(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (recipes.isNotEmpty()) {
            for (recipe in recipes) {
                RecipeCard(
                    Modifier
                        .fillMaxWidth(),
                    RecipeCardViewModel(recipe.id)
                ) {
                    navController.navigate("RecipePage/${recipe.id}")
                }
            }
        } else {
            for (i in 1..4) {
                ShimmerRecipeCard(
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}
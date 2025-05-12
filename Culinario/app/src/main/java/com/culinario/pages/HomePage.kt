package com.culinario.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.culinario.controls.Header
import com.culinario.controls.RecipeCard
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.repository.recipe.RecipeRepository
import com.culinario.mvp.models.repository.user.UserRepository
import com.culinario.viewmodels.RecipePageViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    recipeRepository: RecipeRepository,
    userRepository: UserRepository,
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

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
                TopPageImages(recipeRepository.getAllRecipes().takeLast(4), navController)
            }

            Column {
                Header("Все рецепты")
                AllRecipes(
                    recipeRepository.getAllRecipes(),
                    userRepository, recipeRepository,
                    Modifier,
                    navController
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopPageImages(
    recipes: List<Recipe>,
    navController: NavController
) {
    val carouselState = rememberCarouselState(
        initialItem = 0,
        itemCount = { recipes.count() }
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
                    navController.navigate("RecipePage/${recipes[index].id}")
                }
        )  {
            AsyncImage(
                model = recipes[index].recipeImageResources.recipeBackgroundImageUri,
                contentDescription = "bgImage",
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = recipes[index].name,
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
    userRepository: UserRepository,
    recipeRepository: RecipeRepository,
    modifier: Modifier,
    navController: NavController,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        for (recipe in recipes) {
            RecipeCard(
                RecipePageViewModel(
                    recipe.id,
                    recipeRepository,
                    userRepository,
                    LocalContext.current
                ),
                Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                navController
            )
        }
    }
}
package com.culinario.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.culinario.mvp.models.RecipeRepositoryImpl
import com.culinario.pages.FavoriteRecipesPage
import com.culinario.pages.RecipePage
import com.culinario.pages.SerializationDemoPage
import com.culinario.pages.UserPage

@Composable
fun MainScreen() {
    FavoriteRecipesPage()
}

@Composable
fun ContentScreen(
    modifier: Modifier,
    selectedPageIndex: Int
) {
    when (selectedPageIndex) {
        0 -> SerializationDemoPage(modifier)
        1 -> FavoriteRecipesPage()
        2 -> UserPage()
        3 -> RecipePage(RecipeRepositoryImpl().getAllRecipes().first())
    }
}

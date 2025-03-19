package com.culinario.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.culinario.mvp.models.RecipeRepositoryImpl
import com.culinario.pages.FavoriteRecipesPage
import com.culinario.pages.RecipePage
import com.culinario.pages.SerializationDemoPage
import com.culinario.pages.UserPage
import com.culinario.ui.other.NavItem

@Composable
fun MainScreen() {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val navItems = arrayOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Saved", Icons.Default.Favorite),
        NavItem("Account", Icons.Default.AccountCircle),
        NavItem("Recipe", Icons.Default.Info)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, x ->
                    NavigationBarItem (
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon (
                                imageVector = x.icon,
                                contentDescription = x.label
                            )
                        },
                        label = {
                            Text(x.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(Modifier.padding(innerPadding), selectedIndex)
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier,
    selectedPageIndex: Int
) {
    when (selectedPageIndex) {
        0 -> SerializationDemoPage(modifier)
        1 -> FavoriteRecipesPage()
        2 -> UserPage(modifier)
        3 -> RecipePage(RecipeRepositoryImpl().getAllRecipes().first())
    }
}
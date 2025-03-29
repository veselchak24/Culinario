package com.culinario.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
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
import androidx.navigation.NavController
import com.culinario.mvp.models.repository.RecipeRepository
import com.culinario.mvp.models.repository.UserRepository
import com.culinario.pages.FavoriteRecipesPage
import com.culinario.pages.HomePage
import com.culinario.pages.UserPage
import com.culinario.ui.other.NavItem
import com.culinario.viewmodels.UserPageViewModel

@Composable
fun MainScreen(repository: RecipeRepository, userRepository: UserRepository, navController: NavController) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val navItems = arrayOf (
        NavItem("Home", Icons.Default.Home),
        NavItem("Saved", Icons.Default.Favorite),
        NavItem("Account", Icons.Default.AccountCircle)
    )

    Scaffold (
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
        ContentScreen (
            modifier = Modifier.padding(innerPadding),
            selectedPageIndex = selectedIndex,
            recipeRepository = repository,
            userRepository = userRepository,
            navController
        )
    }
}

@Composable
fun ContentScreen (
    modifier: Modifier,
    selectedPageIndex: Int,
    recipeRepository: RecipeRepository,
    userRepository: UserRepository,
    navController: NavController
) {
    val userPageViewModel = UserPageViewModel("85t6ir7f12v", userRepository, recipeRepository)

    when (selectedPageIndex) {
        0 -> HomePage()
        1 -> FavoriteRecipesPage(userRepository, recipeRepository, modifier, navController)
        2 -> UserPage(modifier, userPageViewModel, navController)
    }
}
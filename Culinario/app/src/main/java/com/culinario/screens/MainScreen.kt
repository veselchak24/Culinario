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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.culinario.backend.DEFAULT_USER_ID
import com.culinario.backend.PREFERENCES_LOCAL_USER_KEY
import com.culinario.helpers.PreferencesManager
import com.culinario.helpers.SavePlaceholderData
import com.culinario.mvp.presenters.recipe.RecipeRepository
import com.culinario.mvp.presenters.user.SelfUserPresenter
import com.culinario.pages.FavoriteRecipesPage
import com.culinario.pages.HomePage
import com.culinario.pages.UserPage
import com.culinario.ui.other.NavItem
import com.culinario.mvp.views.UserView

@Composable
fun MainScreen(repository: RecipeRepository, selfUserPresenter: SelfUserPresenter, navController: NavController) {
    var selectedIndex by rememberSaveable {
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
            selfUserPresenter = selfUserPresenter,
            navController
        )
    }
}

@Composable
fun ContentScreen (
    modifier: Modifier,
    selectedPageIndex: Int,
    recipeRepository: RecipeRepository,
    selfUserPresenter: SelfUserPresenter,
    navController: NavController
) {
    val preferencesManager = PreferencesManager(LocalContext.current)
    val userId = preferencesManager.getData(PREFERENCES_LOCAL_USER_KEY, DEFAULT_USER_ID)

    val userView = UserView(userId, selfUserPresenter, recipeRepository)

    SavePlaceholderData(selfUserPresenter, recipeRepository, LocalContext.current)

    when (selectedPageIndex) {
        0 -> HomePage()
        1 -> FavoriteRecipesPage(selfUserPresenter, recipeRepository, modifier, navController)
        2 -> UserPage(modifier, userView, navController)
    }
}
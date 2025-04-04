package com.culinario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.culinario.backend.PREFERENCES_LOCAL_USER_KEY
import com.culinario.helpers.PreferencesManager
import com.culinario.helpers.SavePlaceholderData
import com.culinario.mvp.presenters.recipe.LocalSaveRecipeRepository
import com.culinario.mvp.presenters.recipe.RecipeRepository
import com.culinario.mvp.presenters.recipe.RecipeRepositoryImpl
import com.culinario.mvp.presenters.user.LocalSaveUserRepository
import com.culinario.mvp.presenters.user.UserRepository
import com.culinario.mvp.presenters.user.UserRepositoryImpl
import com.culinario.pages.RecipeCreatePage
import com.culinario.pages.RecipePage
import com.culinario.pages.UserPage
import com.culinario.screens.LoginScreen
import com.culinario.screens.MainScreen
import com.culinario.ui.theme.CulinarioTheme
import com.culinario.mvp.views.RecipePageViewModel
import com.culinario.mvp.views.UserPageViewModel
import kotlinx.serialization.Serializable

@Serializable
object SignIn

@Serializable
object Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CulinarioTheme {
                SavePlaceholderData(UserRepositoryImpl(), RecipeRepositoryImpl(), LocalContext.current).saveIfFilesNotExists()

                val localUserRepository = LocalSaveUserRepository(LocalContext.current)

                Screens (
                    loginScreen = { LoginScreen(it,  localUserRepository) },
                    homeScreen = { MainScreen(LocalSaveRecipeRepository(LocalContext.current), localUserRepository, it) }
                )
            }
        }

    }

    @Composable
    fun Screens(loginScreen: @Composable (onClick: () -> Unit) -> Unit, homeScreen: @Composable (NavController) -> Unit) {
        initNavController (
            startDestination = if (PreferencesManager(LocalContext.current).hasKey(PREFERENCES_LOCAL_USER_KEY)) Home else SignIn,
            signIn = loginScreen,
            home = homeScreen,
            LocalSaveRecipeRepository(LocalContext.current),
            LocalSaveUserRepository(LocalContext.current)
        )
    }

    @Composable
    fun initNavController(startDestination: Any, signIn: @Composable (() -> Unit) -> Unit, home: @Composable (NavController) -> Unit, recipeRepository: RecipeRepository, userRepository: UserRepository): NavController {
        val navController = rememberNavController()

        NavHost(navController, startDestination = startDestination) {
            composable<SignIn> {
                signIn {
                    navController.navigate(route = Home)
                    navController.clearBackStack<SignIn>()
                }
            }
            composable (
                route = "RecipePage/{recipeID}",
                arguments = listOf(navArgument("recipeID") { type = NavType.StringType })
            ) {
                val recipeId = it.arguments?.getString("recipeID")!!
                RecipePage(RecipePageViewModel(recipeId, recipeRepository, userRepository, LocalContext.current), Modifier, navController)
            }
            composable (
                route = "RecipeCreatePage/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.StringType })
            ) {
                RecipeCreatePage(Modifier, navController, it.arguments?.getString("userId")!!, recipeRepository)
            }
            composable (
                route = "UserPage/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.StringType } )
            ) {
                val userId = it.arguments?.getString("userId")!!
                UserPage(Modifier, UserPageViewModel(userId, userRepository, recipeRepository), navController)
            }
            composable<Home> {
                home(navController)
            }
        }

        return navController
    }
}
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
import com.culinario.backend.PROFILE_JSON_FILE_NAME
import com.culinario.mvp.models.repository.recipe.RecipeRepository
import com.culinario.mvp.models.repository.recipe.RecipeRepositoryImpl
import com.culinario.mvp.models.repository.user.UserRepository
import com.culinario.mvp.models.repository.user.UserRepositoryImpl
import com.culinario.pages.RecipePage
import com.culinario.pages.SerializationDemoPage
import com.culinario.pages.UserPage
import com.culinario.screens.LoginScreen
import com.culinario.screens.MainScreen
import com.culinario.ui.theme.CulinarioTheme
import com.culinario.viewmodels.RecipePageViewModel
import com.culinario.viewmodels.UserPageViewModel
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
object SignIn

@Serializable
object Home

@Serializable
object RecipePage

@Serializable
object RecipeCreatePage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CulinarioTheme {
                Screens (
                    loginScreen = { LoginScreen(it) },
                    homeScreen = { navController -> MainScreen(RecipeRepositoryImpl(), UserRepositoryImpl(), navController) }
                )
            }
        }
    }

    @Composable
    fun Screens(loginScreen: @Composable (onClick: () -> Unit) -> Unit, homeScreen: @Composable (NavController) -> Unit) {
        val context = LocalContext.current
        val file = File(context.applicationContext.filesDir, PROFILE_JSON_FILE_NAME)

        val targetPage: Any = if (!file.exists()) SignIn else Home
        println(targetPage)

        initNavController (
            startDestination = SignIn,
            signIn = loginScreen,
            home = homeScreen,
            RecipeRepositoryImpl(),
            UserRepositoryImpl()
        )
    }

    @Composable
    fun initNavController(startDestination: Any, signIn: @Composable (() -> Unit) -> Unit, home: @Composable (NavController) -> Unit, recipeRepository: RecipeRepository, userRepository: UserRepository): NavController {
        val navController = rememberNavController()

        NavHost(navController, startDestination = startDestination) {
            composable<SignIn> {
                signIn {
                    navController.navigate(route = Home)
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
                SerializationDemoPage(Modifier, navController, it.arguments?.getString("userId")!!)
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
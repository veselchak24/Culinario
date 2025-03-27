package com.culinario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.culinario.backend.PROFILE_JSON_FILE_NAME
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.repository.RecipeRepository
import com.culinario.mvp.models.repository.RecipeRepositoryImpl
import com.culinario.mvp.models.repository.UserRepositoryImpl
import com.culinario.pages.RecipePage
import com.culinario.screens.LoginScreen
import com.culinario.screens.MainScreen
import com.culinario.ui.theme.CulinarioTheme
import kotlinx.serialization.Serializable
import java.io.File
import kotlin.reflect.typeOf

@Serializable
object SignIn

@Serializable
object Home

@Serializable
object RecipePage

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
            RecipeRepositoryImpl()
        )
    }

    @Composable
    fun initNavController(startDestination: Any, signIn: @Composable (() -> Unit) -> Unit, home: @Composable (NavController) -> Unit, recipeRepository: RecipeRepository): NavController {
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
                RecipePage(recipeRepository.getRecipeById(it.arguments!!.getString("recipeID")!!), Modifier)
            }
            composable<Home> {
                home(navController)
            }
        }

        return navController
    }
}
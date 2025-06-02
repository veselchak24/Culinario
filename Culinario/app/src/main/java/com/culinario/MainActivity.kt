package com.culinario

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
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
import androidx.navigation.navDeepLink
import com.culinario.pages.QrScannerPage
import com.culinario.pages.RecipeCreatePage
import com.culinario.pages.RecipePage
import com.culinario.pages.UserPage
import com.culinario.screens.LoginScreen
import com.culinario.screens.MainScreen
import com.culinario.ui.theme.CulinarioTheme
import com.culinario.viewmodel.RecipeCreatePageViewModel
import com.culinario.viewmodel.RecipePageViewModel
import com.culinario.viewmodel.UserPageViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.serialization.Serializable

@Serializable
object Registration

@Serializable
object Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CulinarioTheme {
                Screens (
                    loginScreen = { LoginScreen(it) },
                    homeScreen = { MainScreen(it) }
                )
            }
        }
    }

    @Composable
    fun Screens(
        loginScreen: @Composable (onClick: () -> Unit) -> Unit,
        homeScreen: @Composable (NavController) -> Unit
    ) {
        initNavController (
            startDestination =  if (Firebase.auth.currentUser == null)
                                    Registration
                                else
                                    Home,
            signIn = loginScreen,
            home = homeScreen
        )
    }

    @Composable
    fun initNavController(
        startDestination: Any,
        signIn: @Composable (() -> Unit) -> Unit,
        home: @Composable (NavController) -> Unit
    ) : NavController {
        val navController = rememberNavController()
        val context = LocalContext.current

        NavHost(navController, startDestination = startDestination) {
            composable<Registration> {
                signIn {
                    navController.popBackStack()
                    navController.navigate(route = Home)
                }
            }
            composable(
                route = "RecipePage/{recipeID}",
                deepLinks = listOf(
                    navDeepLink { uriPattern = "culinario://recipe-page/{recipeID}" }
                ),
                arguments = listOf(navArgument("recipeID") { type = NavType.StringType })
            ) {
                val recipeId = it.arguments?.getString("recipeID")!!

                RecipePage(RecipePageViewModel(recipeId), navController)
            }

            composable(
                route = "RecipeCreatePage/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.StringType })
            ) {
                val userId = it.arguments?.getString("userId")!!

                RecipeCreatePage(Modifier, RecipeCreatePageViewModel(userId, LocalContext.current), navController)
            }

            composable(
                route = "UserPage/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.StringType } )
            ) {
                val userId = it.arguments?.getString("userId")!!

                UserPage(Modifier, UserPageViewModel(userId, LocalContext.current), navController)
            }

            composable(
                route = "QrCodeScannerPage"
            ) {
                QrScannerPage {
                    try {
                        navController.popBackStack()
                        navController.navigate(deepLink = Uri.parse(it))
                    } catch (exc: Exception) {
                        Toast.makeText(context, "что-то пошло не так", Toast.LENGTH_SHORT).show()

                        navController.navigate(Home)
                    }
                }
            }

            composable<Home> {
                home(navController)
            }
        }

        return navController
    }
}
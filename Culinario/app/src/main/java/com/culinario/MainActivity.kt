package com.culinario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.culinario.helpers.RECIPE_COLLECTION
import com.culinario.helpers.RecipeRepositoryImpl
import com.culinario.helpers.USER_COLLECTION
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
import com.google.firebase.firestore.firestore
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.Serializable

@Serializable
object Registration

@Serializable
object Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        GoogleAuthProvider.create(credentials = GoogleAuthCredentials(serverId = getString(R.string.oauth_id)))
        setContent {
            CulinarioTheme {
                Screens (
                    loginScreen = { LoginScreen(it) },
                    homeScreen = { MainScreen(it) }
                )
                LaunchedEffect(Unit) {
                    println(Firebase.firestore.collection(USER_COLLECTION).get().await().size())
                }

                //sendRecipesToDb()
            }
        }
    }

    @Composable
    private fun sendRecipesToDb() {
        val recipes = RecipeRepositoryImpl()

        LaunchedEffect(Unit) {
            for (recipe in recipes.recipes) {
                Firebase.firestore.collection(RECIPE_COLLECTION).document(recipe.id).set(recipe).await()
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

        NavHost(navController, startDestination = startDestination) {
            composable<Registration> {
                signIn {
                    navController.popBackStack()
                    navController.navigate(route = Home)
                }
            }

            composable(
                route = "RecipePage/{recipeID}",
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

            composable<Home> {
                home(navController)
            }
        }

        return navController
    }
}
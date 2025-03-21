package com.culinario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.culinario.backend.PROFILE_JSON_FILE_NAME
import com.culinario.screens.LoginScreen
import com.culinario.screens.MainScreen
import com.culinario.ui.theme.CulinarioTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class Test(val name: String, val age:Int)

@Serializable
object SignIn

@Serializable
object Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        println(Json.encodeToString<Test>(Test("TEST", 21)))

        setContent {
            CulinarioTheme {
                Screens (
                    loginScreen = { LoginScreen(it) },
                    homeScreen = { MainScreen() }
                )
            }
        }
    }

    @Composable
    fun Screens(loginScreen: @Composable (onClick: () -> Unit) -> Unit, homeScreen: @Composable () -> Unit) {
        val context = LocalContext.current
        val file = File(context.applicationContext.filesDir, PROFILE_JSON_FILE_NAME)

        val targetPage: Any = if (!file.exists()) SignIn else Home
        println(targetPage)

        initNavController (
            startDestination = SignIn,
            signIn = loginScreen,
            home = homeScreen
        )
    }

    @Composable
    fun initNavController(startDestination: Any, signIn: @Composable (() -> Unit) -> Unit, home: @Composable () -> Unit): NavController {
        val navController = rememberNavController()

        NavHost(navController, startDestination = startDestination) {
            composable<SignIn> {
                signIn {
                    navController.navigate(route = Home)
                }
            }
            composable<Home> {
                home()
            }
        }

        return navController
    }
}
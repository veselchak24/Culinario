package com.culinario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.culinario.backend.LocalRecipeSaverLoader
import com.culinario.mvp.models.Recipe
import com.culinario.ui.theme.CulinarioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CulinarioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val context = LocalContext.current

                    NavHost(navController = navController, startDestination = "Main") {
                        composable("Main") {
                            MainPage(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable("RecipePage") {
                            RecipePage(LocalRecipeSaverLoader().load("test.json", context) as Recipe, Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}


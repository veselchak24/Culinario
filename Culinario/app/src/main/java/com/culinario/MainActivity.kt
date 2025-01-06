package com.culinario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.culinario.backend.LocalRecipeSaverLoader
import com.culinario.backend.LocalRecipesHandler
import com.culinario.mvp.models.Recipe
import com.culinario.pages.FavoriteRecipesPage
import com.culinario.pages.RecipePage
import com.culinario.pages.SerializationDemoPage
import com.culinario.ui.other.NavItem
import com.culinario.ui.theme.CulinarioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val navItems = arrayOf(
            NavItem("Home", Icons.Default.Home),
            NavItem("Saved", Icons.Default.Favorite),
            NavItem("Account", Icons.Default.AccountCircle)
        )

        setContent {
            var selectedIndex by remember {
                mutableIntStateOf(0)
            }

            CulinarioTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            navItems.forEachIndexed { index, x ->
                                NavigationBarItem (
                                    selected = selectedIndex == index,
                                    onClick = { selectedIndex = index },
                                    icon = {
                                        Icon(
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

                    val navController = rememberNavController()
                    val context = LocalContext.current

                    NavHost(navController = navController, startDestination = "Main") {
                        composable("Main") {
                            SerializationDemoPage(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable("RecipePage") {
                            RecipePage(
                                LocalRecipeSaverLoader(R.string.json_save_file_name.toString()).load(
                                    "",
                                    context
                                ) as Recipe, Modifier.padding(innerPadding)
                            )
                        }
                    }

                    ContentScreen(Modifier.padding(innerPadding), selectedIndex)
                }
            }
        }
    }

    @Composable
    fun ContentScreen(modifier: Modifier, selectedPageIndex: Int) {
        LocalRecipesHandler.UpdateLocalRecipes(LocalContext.current)

        LocalRecipesHandler.GetLocalRecipes(LocalContext.current).forEach {
            item -> println(item.cookingSpeed)
        }

        when (selectedPageIndex) {
            0 -> SerializationDemoPage(modifier)
            1 -> FavoriteRecipesPage()
            2 -> RecipePage(
                LocalRecipesHandler.GetLocalRecipes(LocalContext.current).first(),
                modifier
            )
        }
    }
}

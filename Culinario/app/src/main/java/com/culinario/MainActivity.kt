package com.culinario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.culinario.backend.LocalRecipeSaverLoader
import com.culinario.backend.LocalRecipesHandler
import com.culinario.backend.PROFILE_JSON_FILE_NAME
import com.culinario.mvp.models.Recipe
import com.culinario.pages.FavoriteRecipesPage
import com.culinario.pages.RecipePage
import com.culinario.pages.SerializationDemoPage
import com.culinario.screens.LoginScreen
import com.culinario.screens.MainScreen
import com.culinario.ui.other.NavItem
import com.culinario.ui.theme.CulinarioTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CulinarioTheme {
                Screens (
                    loginScreen = {
                        LoginScreen {
                            MainScreen()
                        }
                    },
                    mainScreen = {
                        MainScreen()
                    }
                )
            }
        }
    }

    @Composable
    fun Screens(loginScreen: @Composable (@Composable () -> Unit) -> Unit, mainScreen: @Composable () -> Unit) {
        val context = LocalContext.current
        val file = File(context.applicationContext.filesDir, PROFILE_JSON_FILE_NAME)

        if (!file.exists())
            loginScreen {
                mainScreen()
            }
        else
            mainScreen()
    }
}
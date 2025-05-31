package com.culinario.screens

import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.culinario.helpers.sendBitmapToBackend
import com.culinario.pages.CameraPage
import com.culinario.pages.FavoriteRecipesPage
import com.culinario.pages.HomePage
import com.culinario.pages.UserPage
import com.culinario.ui.other.NavItem
import com.culinario.viewmodel.FavoritePageViewModel
import com.culinario.viewmodel.HomePageViewModel
import com.culinario.viewmodel.UserPageViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun MainScreen(
    navController: NavController
) {
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val navItems = arrayOf(
        NavItem("Главная", Icons.Default.Home),
        NavItem("Любимые", Icons.Default.Favorite),
        NavItem("Профиль", Icons.Default.AccountCircle),
        NavItem("Камера", Icons.Default.Search)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, x ->
                    NavigationBarItem(
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
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedPageIndex = selectedIndex,
            navController
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier,
    selectedPageIndex: Int,
    navController: NavController
) {
    val context = LocalContext.current

    val detectedFruits = remember { mutableStateOf(listOf<String>()) }
    val isProcessing = remember { mutableStateOf(false) }

    val currentUserId = Firebase.auth.currentUser?.uid ?: ""

    when (selectedPageIndex) {
        0 -> HomePage(HomePageViewModel(), navController)
        1 -> FavoriteRecipesPage(modifier, FavoritePageViewModel(), navController)
        2 -> {
            UserPage(modifier, UserPageViewModel(currentUserId, context), navController)
        }
        3 -> CameraPage(
            modifier = modifier,
            onImagePicked = { bitmap: Bitmap ->
                sendBitmapToBackend(isProcessing, bitmap, detectedFruits)
            },
            detectedFruits = detectedFruits.value,
            isProcessing = isProcessing.value
        )
    }
}
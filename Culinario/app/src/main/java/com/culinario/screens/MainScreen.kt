package com.culinario.screens

import android.graphics.Bitmap
import okhttp3.*
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.culinario.backend.DEFAULT_USER_ID
import com.culinario.backend.PREFERENCES_LOCAL_USER_KEY
import com.culinario.helpers.PreferencesManager
import com.culinario.helpers.SavePlaceholderData
import com.culinario.mvp.models.repository.recipe.RecipeRepository
import com.culinario.mvp.models.repository.user.UserRepository
import com.culinario.pages.CameraPage
import com.culinario.pages.FavoriteRecipesPage
import com.culinario.pages.HomePage
import com.culinario.pages.UserPage
import com.culinario.ui.other.NavItem
import com.culinario.viewmodels.UserPageViewModel
import com.google.gson.Gson
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

@Composable
fun MainScreen(
    repository: RecipeRepository,
    userRepository: UserRepository,
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
            recipeRepository = repository,
            userRepository = userRepository,
            navController
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier,
    selectedPageIndex: Int,
    recipeRepository: RecipeRepository,
    userRepository: UserRepository,
    navController: NavController
) {
    val preferencesManager = PreferencesManager(LocalContext.current)
    val userId = preferencesManager.getData(PREFERENCES_LOCAL_USER_KEY, DEFAULT_USER_ID)

    val userPageViewModel = UserPageViewModel(userId, userRepository, recipeRepository)

    SavePlaceholderData(userRepository, recipeRepository, LocalContext.current)

    when (selectedPageIndex) {
        0 -> HomePage(recipeRepository, userRepository, navController)
        1 -> FavoriteRecipesPage(userRepository, recipeRepository, modifier, navController)
        2 -> UserPage(modifier, userPageViewModel, navController)
        3 -> CameraPage(modifier) { bitmap: Bitmap ->
            val byteArray = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray)
            val requestBody =
                byteArray.toByteArray().toRequestBody("image/jpeg".toMediaTypeOrNull())

            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://culinarioai.anopka.keenetic.pro/predict/")
                .post(requestBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: java.io.IOException) {
                    println("Error: ${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.body?.string() == "error")
                        return

                    val gson = Gson()
                    val json = gson.fromJson(response.body?.string(), JsonObject::class.java)
                    val result = mutableMapOf<String, Int>();

                    for (key in json["fruit"]!!.jsonArray) {
                        val name = key.jsonPrimitive.content
                        if (result.containsKey(name))
                            result[name] = result[name]!! + 1
                        else
                            result[name] = 1
                    }

                    for (kv in result) {
                        println(kv.key + " : " + kv.value)
                    }
                }
            })
        }
    }
}
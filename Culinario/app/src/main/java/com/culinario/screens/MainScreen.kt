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
import androidx.navigation.NavController
import com.culinario.pages.CameraPage
import com.culinario.pages.FavoriteRecipesPage
import com.culinario.pages.HomePage
import com.culinario.pages.UserPage
import com.culinario.ui.other.NavItem
import com.culinario.viewmodel.HomePageViewModel
import com.culinario.viewmodel.UserPageViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.ByteArrayOutputStream

@Composable
fun MainScreen(
    navController: NavController
) {
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(2)
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
    var detectedFruits by remember { mutableStateOf<List<String>>(emptyList()) }
    var isProcessing by remember { mutableStateOf(false) }

    when (selectedPageIndex) {
        0 -> HomePage(HomePageViewModel(), navController)
        1 -> FavoriteRecipesPage(modifier, navController)
        2 -> UserPage(modifier, UserPageViewModel(Firebase.auth.currentUser?.uid ?: ""), navController)
        3 -> CameraPage(
            modifier = modifier,
            onImagePicked = { bitmap: Bitmap ->
                isProcessing = true

                val byteArray = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray)
                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(
                        "file",
                        "image.jpg",
                        byteArray.toByteArray().toRequestBody("image/jpeg".toMediaTypeOrNull())
                    )
                    .build()

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
                        if (!response.isSuccessful) return

                        val responseBody = response.body?.string() ?: return

                        // Используем Gson для парсинга в Map
                        val gson = Gson()
                        val type = object : TypeToken<Map<String, Any>>() {}.type
                        val responseMap = gson.fromJson<Map<String, Any>>(responseBody, type)

                        if (responseMap.containsKey("error")) {
                            println("Error: ${responseMap["error"]}")
                            isProcessing=false
                            return
                        }

                        // Извлекаем список фруктов
                        detectedFruits = responseMap["fruit"] as? List<String> ?: emptyList()
                        isProcessing=false
                    }
                })
            },
            detectedFruits = detectedFruits,
            isProcessing = isProcessing,
        )
    }
}
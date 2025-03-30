package com.culinario.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.culinario.mvp.models.repository.user.LocalSaveUserRepository
import com.culinario.mvp.models.repository.user.UserRepositoryImpl
import com.culinario.pages.SignInPage
import com.culinario.pages.SignUpPage

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    LocalSaveUserRepository(LocalContext.current).addUser(UserRepositoryImpl().getUserById("85t6ir7f12v"))

    Scaffold { innerPadding ->
        val pagerState = rememberPagerState (
            initialPage = 6,
            pageCount = { 7 }
        )

        var close by remember { mutableStateOf(false) }

        val coroutineScope = rememberCoroutineScope()

        if (close)
            return@Scaffold

        HorizontalPager (
            state = pagerState,
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) { page ->
            Box (
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box (
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(start = 40.dp, end = 40.dp)
                        .align(Alignment.TopStart)
                ) {
                    when (page) {
                        5 -> {
                            if (
                                SignInPage (
                                    modifier = Modifier
                                        .padding(innerPadding)
                                )
                            ) {
                                onLogin()
                                close = true
                            }
                        }
                        6 -> {
                            if (
                                SignUpPage (
                                    Modifier
                                        .padding(innerPadding),
                                    coroutineScope,
                                    pagerState,
                                    page - 1
                                )
                            ) {
                                onLogin()
                                close = true
                            }
                        }
                        else -> {
                            Text(
                                text = "Presentation page №$page",
                                style = MaterialTheme.typography.displayLarge,
                                modifier = Modifier
                                    .padding(innerPadding)
                            )
                        }
                    }
                }
            }

        }
    }
}
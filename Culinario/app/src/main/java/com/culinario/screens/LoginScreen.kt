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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.culinario.mvp.models.repository.user.LocalSaveUserRepository
import com.culinario.mvp.models.repository.user.UserRepository
import com.culinario.mvp.models.repository.user.UserRepositoryImpl
import com.culinario.pages.SignInPage
import com.culinario.pages.SignUpPage

@Composable
fun LoginScreen(onLogin: () -> Unit, userRepository: UserRepository) {
    LocalSaveUserRepository(LocalContext.current).addUser(UserRepositoryImpl().getUserById("85t6ir7f12v"))

    Scaffold { innerPadding ->
        val pagerState = rememberPagerState (
            initialPage = 1,
            pageCount = { 2 }
        )

        val coroutineScope = rememberCoroutineScope()

        HorizontalPager (
            state = pagerState,
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) { page ->

            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(start = 40.dp, end = 40.dp)
            ) {
                when (page) {
                    0 -> {

                        SignInPage (
                            modifier = Modifier
                                .padding(innerPadding)
                        ) {
                            onLogin()
                        }
                    }
                    1 -> {
                        SignUpPage (
                            Modifier
                                .padding(innerPadding),
                            coroutineScope,
                            pagerState,
                            userRepository,
                            page - 1
                        ) {
                            onLogin()
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
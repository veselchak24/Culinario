package com.culinario.screens

import com.culinario.pages.SignInPage
import com.culinario.pages.SignUpPage
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(mainScreenHook: @Composable () -> Unit) {
    Scaffold { innerPadding ->
        val pagerState = rememberPagerState (
            initialPage = 6,
            pageCount = { 7 }
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
            ) {
                Box (
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(start = 40.dp, end = 40.dp)
                        .align(Alignment.TopStart)
                ) {
                    when (page) {
                        5 -> {
                            SignInPage(
                                modifier = Modifier
                                    .padding(innerPadding)
                            )
                        }
                        6 -> {
                            SignUpPage (
                                Modifier
                                    .padding(innerPadding),
                                coroutineScope,
                                pagerState,
                                page - 1
                            ) {
                                mainScreenHook()
                            }
                        }
                        else -> Text(
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
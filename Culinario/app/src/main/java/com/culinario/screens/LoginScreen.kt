package com.culinario.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culinario.R
import com.culinario.pages.SignInPage
import com.culinario.pages.SignUpPage
import com.culinario.ui.theme.ancizarSerifFontFamily
import com.culinario.viewmodel.LoginViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.mmk.kmpauth.uihelper.google.GoogleSignInButton

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    val loginAndPasswordLoginSelected = remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        BackHandler {
            if (loginAndPasswordLoginSelected.value)
                loginAndPasswordLoginSelected.value = false
        }

        if (loginAndPasswordLoginSelected.value.not()) {
            MainRegistrationPage(
                loginAndPasswordLoginSelected
            ) {
                onLogin()
            }
        } else {
            LoginAndPasswordRegistrationPage(
                modifier = Modifier.padding(innerPadding)
            ) {
                onLogin()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainRegistrationPage(
    selectionState: MutableState<Boolean> = mutableStateOf(false),
    onLogin: () -> Unit = { }
) {
    val loginViewModel = LoginViewModel()

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.login_screen_no_text),
                contentDescription = "Splash screen background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 105.dp),
                fontFamily = ancizarSerifFontFamily,
                text = "Culinario",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W800,
                fontSize = 85.sp,
                color = Color.White
            )
        }

        Column(
            modifier = Modifier
                .padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Вход",
                fontWeight = FontWeight.W700,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            GoogleButtonUiContainerFirebase(
                onResult = { result ->
                    if (result.isSuccess) {
                        loginViewModel.onGoogleAuth(Firebase.auth.currentUser!!) {
                            onLogin()
                        }
                    }
                },
                linkAccount = false,
                filterByAuthorizedAccounts = false
            ) {
                GoogleSignInButton(
                    onClick = {
                        this.onClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Войти через Google"
                )
            }

            TextButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    selectionState.value = true
                }
            ) {
                Text(
                    text = "Войти по логину и паролю"
                )
            }
        }

    }
}

@Composable
private fun LoginAndPasswordRegistrationPage(
    modifier: Modifier,
    onLogin: () -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { 2 }
    )

    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxSize()
    ) { page ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 40.dp, end = 40.dp)
        ) {
            when (page) {
                0 -> {

                    SignInPage(
                        modifier = modifier
                    ) {
                        onLogin()
                    }
                }

                1 -> {
                    SignUpPage(
                        modifier,
                        coroutineScope,
                        pagerState,
                        page - 1
                    ) {
                        onLogin()
                    }
                }

                else -> {
                    Text(
                        text = "Presentation page №$page",
                        style = MaterialTheme.typography.displayLarge,
                        modifier = modifier
                    )
                }
            }
        }

    }
}
package com.culinario.pages

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.culinario.backend.PROFILE_JSON_FILE_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SignUpPage(
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
    signInPageIndex: Int = 0,
    mainScreen: @Composable () -> Unit
) {
    var goToMainScreen by remember { mutableStateOf(false) }

    val birthDay by remember { mutableDoubleStateOf(0.0)  }

    var nicknameText by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var repeatPasswordText by remember { mutableStateOf("") }

    val context = LocalContext.current

    if (goToMainScreen)
        mainScreen()

    Scaffold (
        modifier = modifier
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                "Sign Up",
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = "Welcome back!\nYou've been gone for so long =(",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Basic info",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = 25.dp)
            )

            TextField (
                modifier = Modifier
                    .fillMaxWidth(),
                value = nicknameText,
                onValueChange = { newText ->
                    nicknameText = newText
                },
                label = {
                    Text("Nick name")
                },
                maxLines = 1
            )

            TextField (
                modifier = Modifier
                    .fillMaxWidth(),
                value = emailText,
                onValueChange = { newText ->
                    emailText = newText
                },
                label = {
                    Text("E-mail")
                },
                maxLines = 1
            )

            Text(
                text = "Password",
                style = MaterialTheme.typography.titleMedium
            )

            TextField (
                modifier = Modifier
                    .fillMaxWidth(),
                value = passwordText,
                onValueChange = { newText ->
                    passwordText = newText
                },
                label = {
                    Text("Password")
                },
                maxLines = 1
            )
            TextField (
                modifier = Modifier
                    .fillMaxWidth(),
                value = repeatPasswordText,
                onValueChange = { newText ->
                    repeatPasswordText = newText
                },
                label = {
                    Text("Repeat password")
                },
                maxLines = 1
            )

            Row {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    onClick = {
                        // TODO: создание объекта пользователя
                        context.openFileOutput(PROFILE_JSON_FILE_NAME, Context.MODE_PRIVATE).use {
                            it.write(0)
                            goToMainScreen = true
                        }

                    }
                ) {
                    Text("Sign Up")
                }

                TextButton (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp),
                    onClick = {
                        // TODO: создание пустой учётной записи
                    }
                ) {
                    Text("As guest")
                }
            }
        }

        Box (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button (
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomStart),
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(signInPageIndex)
                    }
                }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    "Sign In",
                    modifier = Modifier
                )
                Text("Sign In")
            }
        }
    }
}
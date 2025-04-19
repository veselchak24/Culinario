package com.culinario.pages

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.culinario.backend.PREFERENCES_LOCAL_USER_KEY
import com.culinario.helpers.PreferencesManager
import com.culinario.mvp.models.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random


@SuppressLint("ComposableNaming")
@Composable
fun SignUpPage (
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
    selfUserPresenter: com.culinario.mvp.presenters.user.SelfUserPresenter,
    signInPageIndex: Int = 0
): Boolean {
    var loginSuccess by remember { mutableStateOf(false) }

    var nicknameText by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var repeatPasswordText by remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold (
        modifier = modifier
    ) { innerPadding ->
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

            Column (
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
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
            }

            Spacer(Modifier.height(10.dp))

            Column (
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
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
                    maxLines = 1,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
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
                    maxLines = 1,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }

            Button (
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    val answer = validateUserData(emailText, passwordText, repeatPasswordText)

                    println("$answer: email validate: ${passwordText == repeatPasswordText}")
                    if (answer.not()) return@Button

                    saveUser(nicknameText, emailText, selfUserPresenter, context)
                    loginSuccess = true
                }
            ) {
                Text("Sign Up")
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

    return loginSuccess
}

private fun saveUser (
    nicknameText: String,
    emailText: String,
    selfUserPresenter: com.culinario.mvp.presenters.user.SelfUserPresenter,
    context: Context
) {
    val id = Random.nextInt(1000000, 9999999).toString()
    val newUser = UserModel (
        id = id,
        name = nicknameText,
        email = emailText,
        about = "В разработке",
        recipesId = listOf()
    )

    PreferencesManager(context).saveData(PREFERENCES_LOCAL_USER_KEY, id)

    selfUserPresenter.addUser(newUser)
    selfUserPresenter.commit()
}

fun validateUserData(email: String, password: String, repeatPassword: String): Boolean {
    if (password != repeatPassword) return false
    //if (Regex("[A-Za-z0-9-_.]+@[A-Za-z0-9-]+\\.[A-Za-z]{1,3}").matches(email).not()) return false

    return true
}
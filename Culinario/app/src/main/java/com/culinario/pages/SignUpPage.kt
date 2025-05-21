package com.culinario.pages

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.culinario.backend.PREFERENCES_LOCAL_USER_KEY
import com.culinario.helpers.PreferencesManager
import com.culinario.helpers.USER_COLLECTION
import com.culinario.helpers.isTrue
import com.culinario.mvp.models.User
import com.culinario.viewmodel.LoginViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random


@SuppressLint("ComposableNaming")
@Composable
fun SignUpPage (
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
    signInPageIndex: Int = 0,
    onLogin: () -> Unit
) {
    val context = LocalContext.current

    val loginViewModel = LoginViewModel()
    loginViewModel.onException = {
        Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
    }

    var nicknameText by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var repeatPasswordText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            "Регистрация",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "Добро пожаловать в Culinario!",
            style = MaterialTheme.typography.titleMedium
        )

        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextField (
                modifier = Modifier
                    .fillMaxWidth(),
                value = emailText,
                onValueChange = { newText ->
                    emailText = newText
                },
                label = {
                    Text("Почта")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
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
                    Text("Пароль")
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                )
            )

            TextField (
                modifier = Modifier
                    .fillMaxWidth(),
                value = repeatPasswordText,
                onValueChange = { newText ->
                    repeatPasswordText = newText
                },
                label = {
                    Text("И снова пароль")
                },
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
        }

        Button (
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                validateUserData(emailText, passwordText, repeatPasswordText).isTrue {
                    loginViewModel.signUp(nicknameText, emailText, passwordText) {
                        Firebase
                            .firestore
                            .collection(USER_COLLECTION)
                            .document(it?.uid ?: "default")
                            .get()
                            .addOnCompleteListener { task ->
                                if (task.isComplete) {
                                    onLogin()
                                }
                            }

                        //TODO запуск страницы редактирование профиля
                    }
                }
            }
        ) {
            Text("Регистрируюсь")
        }

        Button (
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Start),
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(signInPageIndex)
                }
            }
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                "Назад",
                modifier = Modifier
            )
            Text("Войти")
        }
    }
}

fun validateUserData(email: String, password: String, repeatPassword: String): Boolean {
    return password == repeatPassword
    //if (Regex("[A-Za-z0-9-_.]+@[A-Za-z0-9-]+\\.[A-Za-z]{1,3}").matches(email).not()) return false
}
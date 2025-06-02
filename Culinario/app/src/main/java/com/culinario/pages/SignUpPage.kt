package com.culinario.pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.culinario.helpers.USER_COLLECTION
import com.culinario.mvp.models.User
import com.culinario.viewmodel.LoginViewModel
import com.culinario.viewmodel.UserCreateViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@SuppressLint("ComposableNaming")
@Composable
fun SignUpPage (
    modifier: Modifier,
    onBackToSignIn: () -> Unit,
    onLogin: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val loginViewModel = LoginViewModel()
    loginViewModel.onException = {
        Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
    }

    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var repeatPasswordText by remember { mutableStateOf("") }

    var isCreateUserPage by remember { mutableStateOf(false) }
    var newUserId by remember { mutableStateOf("") }

    if (isCreateUserPage) {
        UserCreatePage(
            Modifier,
            UserCreateViewModel(newUserId, LocalContext.current)
        ) {
            onLogin()
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                "Регистрация",
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = "Добро пожаловать в Culinario!",
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                style = MaterialTheme.typography.titleMedium
            )

            Column (
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp),
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
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
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
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onClick = {
                    if (validateUserData(emailText, passwordText, repeatPasswordText)) {
                        loginViewModel.signUp("", emailText, passwordText) {
                            coroutineScope.launch {
                                if (Firebase
                                        .firestore
                                        .collection(USER_COLLECTION)
                                        .document(it!!.uid)
                                        .get()
                                        .await()
                                        .toObject(User::class.java)!!.name
                                        .isNotEmpty()
                                ) {

                                    Firebase
                                        .firestore
                                        .collection(USER_COLLECTION)
                                        .document(it.uid)
                                        .get()
                                        .addOnCompleteListener { task ->
                                            if (task.isComplete) {
                                                onLogin()
                                            }
                                        }
                                } else {
                                    isCreateUserPage = true

                                    newUserId = it.uid
                                }
                            }
                        }
                    }
                }
            ) {
                Text("Регистрируюсь")
            }

            Button (
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Start)
                    .padding(horizontal = 20.dp),
                onClick = {
                    onBackToSignIn()
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
}

fun validateUserData(email: String, password: String, repeatPassword: String): Boolean  =
    Regex("[A-Za-z0-9-_.]+@[A-Za-z0-9-]+\\.[A-Za-z]{1,3}").matches(email) && password == repeatPassword
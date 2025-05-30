package com.culinario.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.culinario.viewmodel.LoginViewModel


@SuppressLint("ComposableNaming")
@Composable
@Preview(showBackground = true)
fun SignInPage(
    modifier: Modifier = Modifier,
    onSignIn: () -> Unit = { }
) {
    val loginViewModel = LoginViewModel()

    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            "Вход",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "Привет снова!\nНужно срочно узнать рецепт пасты (●'◡'●)",
            style = MaterialTheme.typography.titleMedium
        )

        TextField(
            value = emailState,
            onValueChange = {
                emailState = it
            },
            label = {
                Text(
                    text = "Почта"
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = passwordState,
            onValueChange = {
                passwordState = it
            },
            label = {
                Text(
                    text = "Пароль"
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        
        Column {
            Button(
                onClick = {
                    loginViewModel.signIn(emailState, passwordState) {
                        onSignIn()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Войти")
            }
        }
    }
}
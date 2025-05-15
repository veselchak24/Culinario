package com.culinario.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.mmk.kmpauth.uihelper.google.GoogleSignInButtonIconOnly


@SuppressLint("ComposableNaming")
@Composable
fun SignInPage(
    modifier: Modifier = Modifier,
    onSignIn: () -> Unit
) {
    var isSignIn by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            "Sign In",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Welcome to Culinario app!\nThere you can find many recipes =3",
            style = MaterialTheme.typography.titleMedium
        )

        GoogleButtonUiContainerFirebase(
            onResult = { result ->
                if (result.isSuccess) {
                    println(Firebase.auth.currentUser)

                    onSignIn()
                }
            },
            linkAccount = false,
            filterByAuthorizedAccounts = false
        ) {
            GoogleSignInButtonIconOnly(
                onClick = {
                    this.onClick()
                }
            )
        }

        Button(
            onClick = {
                onSignIn()
            }
        ) {
            Text("Sign In as guest")
        }

        Button(
            onClick = {
                Firebase.auth.signOut()
            }
        ) {
            Text("Sign Out")
        }
    }
}
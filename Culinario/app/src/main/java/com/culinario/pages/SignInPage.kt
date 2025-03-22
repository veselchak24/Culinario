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

@SuppressLint("ComposableNaming")
@Composable
fun SignInPage(modifier: Modifier) : Boolean {
    var isSignIn by remember { mutableStateOf(false) }

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

        Button(
            onClick = { isSignIn = true }
        ) {
            Text("Sign In")
        }
    }

    return isSignIn
}
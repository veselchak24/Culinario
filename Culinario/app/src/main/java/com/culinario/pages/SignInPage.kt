package com.culinario.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignInPage(
    modifier: Modifier
) {
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
    }
}
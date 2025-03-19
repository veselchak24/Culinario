package com.culinario.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserPage(modifier: Modifier) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .weight(.3f)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Text("upper block")
        }
        Spacer(Modifier.height(15.dp))
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .weight(.5f)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Text("lower block")
        }
    }
}
package com.culinario.controls

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight(700),
        fontSize = 18.sp,
        modifier = Modifier
            .padding(start = 10.dp)
    )
}
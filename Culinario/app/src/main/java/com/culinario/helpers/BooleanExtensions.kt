package com.culinario.helpers

import androidx.compose.runtime.Composable

@Composable
fun Boolean.IsTrue(action: @Composable () -> Unit) {
    if (this) {
        action()
    }
}
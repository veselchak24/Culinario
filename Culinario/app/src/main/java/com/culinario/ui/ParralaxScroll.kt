package com.culinario.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import java.lang.reflect.Modifier

//@Composable
//fun Modifier.parallaxLayoutModifier(scrollState: ScrollState, rate: Int) =
//    layout { measurable, constraints ->
//        val placeable = measurable.measure(constraints)
//        val height = if (rate > 0) scrollState.value / rate else scrollState.value
//        layout(placeable.width, placeable.height) {
//            placeable.place(0, height)
//        }
//    }
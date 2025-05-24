package com.culinario.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.culinario.mvp.models.NutritionInfo
import kotlin.math.roundToInt

@Composable
fun NutritionInfo(
    nutritionInfo: NutritionInfo,
    width: Dp = 70.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NutritionInfoItem(
            name = "Калории",
            amount = nutritionInfo.calories.roundToInt(),
            width = width
        )

        NutritionInfoItem(
            name = "Белки",
            amount = nutritionInfo.proteins.roundToInt(),
            width = width
        )

        NutritionInfoItem(
            name = "Жиры",
            amount = nutritionInfo.fats.roundToInt(),
            width = width
        )

        NutritionInfoItem(
            name = "Углеводы",
            amount = nutritionInfo.carbohydrates.roundToInt(),
            width = width
        )
    }
}
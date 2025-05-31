package com.culinario.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.culinario.mvp.models.NutritionInfo
import kotlin.math.roundToInt

@Composable
fun NutritionInfo(nutritionInfo: NutritionInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NutritionInfoItem(
            name = "Калории",
            amount = nutritionInfo.calories.roundToInt()
        )

        NutritionInfoItem(
            name = "Белки",
            amount = nutritionInfo.proteins.roundToInt()
        )

        NutritionInfoItem(
            name = "Жиры",
            amount = nutritionInfo.fats.roundToInt()
        )

        NutritionInfoItem(
            name = "Углеводы",
            amount = nutritionInfo.carbohydrates.roundToInt()
        )
    }
}
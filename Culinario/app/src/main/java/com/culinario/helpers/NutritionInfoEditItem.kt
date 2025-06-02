package com.culinario.helpers

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun NutritionInfoEditItem(
    calories: MutableState<Int>,
    proteins: MutableState<Int>,
    fats: MutableState<Int>,
    carbohydrates: MutableState<Int>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = if (calories.value == 0) "" else calories.value.toString(),
            onValueChange = {
                calories.value = it.toIntOrNull() ?: 0
            },
            label = {
                Text(
                    text = "Калории"
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f),
                value = if (proteins.value == 0) "" else proteins.value.toString(),
                onValueChange = {
                    proteins.value = it.toIntOrNull() ?: 0
                },
                label = {
                    Text(
                        text = "Белки",
                        maxLines = 1,
                        modifier = Modifier
                            .basicMarquee()
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Next
                )
            )
            TextField(
                modifier = Modifier
                    .weight(1f),
                value = if (fats.value == 0) "" else fats.value.toString(),
                onValueChange = {
                    fats.value = it.toIntOrNull() ?: 0
                },
                label = {
                    Text(
                        text = "Жиры",
                        maxLines = 1,
                        modifier = Modifier
                            .basicMarquee()
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Next
                )
            )
            TextField(
                modifier = Modifier
                    .weight(1f),
                value = if (carbohydrates.value == 0) "" else carbohydrates.value.toString(),
                onValueChange = {
                    carbohydrates.value = it.toIntOrNull() ?: 0
                },
                label = {
                    Text(
                        text = "Углеводы",
                        maxLines = 1,
                        modifier = Modifier
                            .basicMarquee()
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Next
                )
            )
        }
    }
}
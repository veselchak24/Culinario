package com.culinario.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T: Enum<T>> EnumButton(
    modifier: Modifier,
    enum: T,
    label: String,
    enumConverter: (T) -> String,
    onSelect: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                .fillMaxWidth()
                .clickable {
                    expanded = true
                }
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 8.dp)
            ) {
                Text(
                    text = label,
                    fontSize = 12.sp
                )

                Text(
                    text = enumConverter(enum)
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                for (entry in enum::class.java.getEnumConstants()) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = enumConverter(entry)
                            )
                        },
                        onClick = {
                            onSelect(entry)
                            expanded = false
                        }
                    )
                }
            }
        }

    }
}
package com.culinario.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culinario.helpers.QrScanner

@Composable
fun QrScannerPage(
    onTextScan: (text: String) -> Unit
) {
    var isScanned by remember { mutableStateOf(false) }

    QrScanner(
        scannedText = { text ->
            if (isScanned.not()) {
                onTextScan(text)
                isScanned = true
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 150.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Наведите камеру на QR рецепта",
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                )

                Box(
                    Modifier
                        .fillMaxWidth(.6f)
                        .aspectRatio(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                border = BorderStroke(
                                    width = 2.dp,
                                    color = Color.White
                                ),
                                shape = RoundedCornerShape(25.dp)
                            )
                            .align(Alignment.Center)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .border(
                                border = BorderStroke(
                                    width = 2.dp,
                                    color = Color.White
                                ),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}
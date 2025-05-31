package com.culinario.controls

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.culinario.R
import com.culinario.mvp.models.DetailedCookingStep

@Preview(showBackground = true)
@Composable
fun DetailedStepPageCard(
    modifier: Modifier = Modifier,
    detailedCookingStep: DetailedCookingStep = DetailedCookingStep()
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            model = detailedCookingStep.imageUrl,
            contentDescription = "detailed cooking step background image",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        Column(
            modifier = Modifier
                .padding(top = 200.dp, start = 15.dp, end = 15.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = detailedCookingStep.title,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier
                        .basicMarquee()
                        .fillMaxWidth(.8f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.clock_icon),
                        contentDescription = "clock icon",
                        modifier = Modifier
                            .size(22.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "${(detailedCookingStep.time / 60).coerceIn(1..999)} мин."
                    )
                }
            }

            Text(
                text = detailedCookingStep.description
            )
        }
    }
}
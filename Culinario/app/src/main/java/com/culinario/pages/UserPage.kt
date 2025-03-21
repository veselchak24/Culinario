package com.culinario.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.culinario.R

@Preview
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun UserPage(modifier: Modifier = Modifier) {
    Scaffold { _ ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp)
            ) {
                Image (
                    contentDescription = "userBackground",
                    painter = painterResource(R.drawable.user_background_placeholder),
                    modifier = Modifier
                        .padding(bottom = 80.dp)
                        .fillMaxSize()
                        .alpha(0.4f)
                        .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 60.dp, bottomEnd = 60.dp)),
                    contentScale = ContentScale.Crop
                )

                Card (
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 15.dp
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(start = 15.dp, end = 15.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )
                ) {
                    Box {
                        Column (
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 80.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text (
                                text = stringResource(R.string.placeholder_nickname),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight(700)
                            )

                            Text (
                                text = "seregogy@gmail.com",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight(200)
                            )
                        }

                        Row (
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                        ) {
                            Button (
                                onClick = { },
                                modifier = Modifier
                                    .weight(.8f)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(5.dp)
                            ) {
                                Text("Подписаться")
                            }
                        }
                    }
                }

                Box (
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(130.dp)
                        .height(130.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    Image (
                        contentDescription = "userAvatar",
                        painter = painterResource(R.drawable.user_avatar_placeholder),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .align(Alignment.Center)
                            .clip(CircleShape)
                    )
                }
            }

            Spacer(Modifier.height(15.dp))

            Column (
                modifier = Modifier
                    .padding(start = 50.dp, end = 50.dp)
                    .fillMaxWidth()
                    .weight(.5f)
            ) {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Stat (
                        header = "Лайки",
                        value = "123"
                    )
                    Stat (
                        header = "Рецепты",
                        value = "6"
                    )
                    Stat (
                        header = "Просмотры",
                        value = "9k"
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.Stat(header: String, value: String) {
    Card (
        modifier = Modifier
            .weight(1f)
            .padding(5.dp)
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                fontWeight = FontWeight(800)
            )

            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .basicMarquee(),
                text = header,
                softWrap = true,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight(300),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}
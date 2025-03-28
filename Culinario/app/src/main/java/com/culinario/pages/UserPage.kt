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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culinario.R
import com.culinario.mvp.models.User

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun UserPage(modifier: Modifier = Modifier, user: User, userActivity: Array<@Composable () -> Unit> = arrayOf()) {
    val scrollState = rememberScrollState()

    Scaffold { _ ->
        Box (
            modifier = modifier
                .verticalScroll(scrollState)
        ) {
            BackgroundImage()

            Column (
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                UserHeader(user.Name, user.Email!!)

                UserAbout(user.About!!)

                UserStats(user.LikesCount.toString(), user.RecipeCount.toString(), user.WatchCount.toString())

                UserActivity(userActivity)
            }
        }
    }
}

@Composable
private fun BackgroundImage() {
    Image(
        contentDescription = "userBackground",
        painter = painterResource(R.drawable.user_background_placeholder),
        modifier = Modifier
            .height(300.dp)
            .fillMaxSize()
            .alpha(0.4f)
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 35.dp,
                    bottomEnd = 35.dp
                )
            ),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun UserHeader(userName: String, userMail: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(370.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 15.dp
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentHeight()
                .padding(start = 25.dp, end = 25.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 75.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userName,
                    fontWeight = FontWeight(900),
                    fontSize = 28.sp
                )

                Text(
                    text = userMail,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight(200)
                )

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(stringResource(R.string.subscribe_button_text))
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .width(130.dp)
                .height(130.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            Image(
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
}

@Composable
fun UserAbout(about: String) {
    Column {
        Header(stringResource(R.string.user_page_header_about))

        Card (
            elevation = CardDefaults.cardElevation(
                defaultElevation = 15.dp
            ),
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        ) {
            Column (
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(
                    text = about,
                    maxLines = 7
                )
            }
        }
    }
}

@Composable
fun UserStats(likesCount: String, recipeCount: String, watchCount: String) {
    Column {
        Header(stringResource(R.string.user_page_header_stats))

        Row(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Stat(
                header = stringResource(R.string.likes),
                value = likesCount
            )
            Stat(
                header = stringResource(R.string.recipes),
                value = recipeCount
            )
            Stat(
                header = stringResource(R.string.watches),
                value = watchCount
            )
        }
    }
}

@Composable
fun UserActivity(composable: Array<@Composable () -> Unit>) {
    Column {
        Header(stringResource(R.string.user_page_header_activity))

        Column(
            Modifier
                .padding(start = 25.dp, end = 25.dp)
        ) {
            composable.forEach { composable ->
                composable()
            }
        }
    }
}

@Composable
public fun Header(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight(700),
        fontSize = 18.sp,
        modifier = Modifier
            .padding(start = 35.dp, end = 35.dp)
    )
}

@Composable
fun RowScope.Stat(header: String, value: String) {
    Card (
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 5.dp)
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
                fontWeight = FontWeight(800),
                fontSize = 18.sp
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
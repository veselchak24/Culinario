package com.culinario.pages

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.culinario.viewmodel.UserCreateViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UserCreatePage(
    modifier: Modifier = Modifier,
    viewModel: UserCreateViewModel,
    onCreate: () -> Unit = { }
) {
    var user by remember { mutableStateOf(viewModel.userState.value) }

    val userName = remember { mutableStateOf("") }
    val userAvatar = remember { mutableStateOf<String?>(null) }

    val userDescription = remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.userState.collect { newState ->
            user = newState
            userName.value = newState.name
            userAvatar.value = newState.imageUrl
        }
    }

    val pagerState = rememberPagerState(initialPage = 0) { 3 }

    BackHandler {
        if (pagerState.currentPage > 0) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        }
    }

    Scaffold { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (viewModel.userLoaded.value.not()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            } else {
                HorizontalPager(
                    state = pagerState,
                    flingBehavior = PagerDefaults.flingBehavior(pagerState)
                ) { index ->
                    when(index) {
                        0 -> HelloPage {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                        1 -> BaseValues(userAvatar, userName, userDescription)
                        else -> Text(
                            text = "IDk"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HelloPage(
    onNextPage: () -> Unit
) {
    var alpha1 by remember { mutableStateOf(0f) }
    var padding1 by remember { mutableStateOf(0f) }

    var alpha2 by remember { mutableStateOf(0f) }
    var padding2 by remember { mutableStateOf(0f) }

    var alpha3 by remember { mutableStateOf(0f) }
    var padding3 by remember { mutableStateOf(0f) }

    val animateFirstAlpha by animateFloatAsState(
        animationSpec = tween(delayMillis = 200, durationMillis = 1000),
        label = "alpha animation",
        targetValue = alpha1,
    )

    val animateFirstPadding by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000),
        label = "padding animation",
        targetValue = padding1,
    )

    val animateSecondAlpha by animateFloatAsState(
        animationSpec = tween(delayMillis = 200, durationMillis = 1000),
        label = "alpha animation",
        targetValue = alpha2,
    )

    val animateSecondPadding by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000),
        label = "padding animation",
        targetValue = padding2,
    )

    val animateThirdAlpha by animateFloatAsState(
        animationSpec = tween(delayMillis = 200, durationMillis = 1000),
        label = "alpha animation",
        targetValue = alpha2,
    )

    val animateThirdPadding by animateFloatAsState(
        animationSpec = tween(durationMillis = 1000),
        label = "padding animation",
        targetValue = padding2,
    )

    LaunchedEffect(Unit) {
        alpha1 = 1f
        padding1 = 25f

        delay(500)

        alpha2 = 1f
        padding2 = 25f

        delay(1000)

        alpha3 = 1f
        padding3 = 25f
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, top = 100.dp, bottom = 20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = "Привет",
                modifier = Modifier
                    .padding(start = animateFirstPadding.dp)
                    .alpha(animateFirstAlpha),
                fontSize = 60.sp,
                fontWeight = FontWeight.W700
            )

            Text(
                text = "давай создадим профиль",
                modifier = Modifier
                    .padding(start = animateSecondPadding.dp)
                    .alpha(animateSecondAlpha),
                lineHeight = 50.sp,
                fontSize = 60.sp,
                fontWeight = FontWeight.W700
            )
        }

        Button(
            onClick = {
                onNextPage()
            },
            modifier = Modifier
                .alpha(animateThirdAlpha)
                .align(Alignment.BottomEnd)
                .padding(end = 10.dp + animateThirdPadding.dp)
        ) {
            Text(
                text = "Начнём!"
            )
        }
    }
}

@Composable
private fun BaseValues(
    avatarUrl: MutableState<String?>,
    userName: MutableState<String>,
    description: MutableState<String>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.Companion
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                model = avatarUrl.value ?: "https://avatars.githubusercontent.com/u/183899995?s=400&v=4",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop
            )

            TextField(
                value = userName.value,
                onValueChange = {
                    userName.value = it
                },
                label = {
                    Text(
                        text = "Ник"
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .width(300.dp)
            )

            TextField(
                value = description.value,
                onValueChange = {
                    description.value = it
                },
                label = {
                    Text(
                        text = "О себе"
                    )
                },
                modifier = Modifier
                    .width(300.dp)
            )
        }
    }
}
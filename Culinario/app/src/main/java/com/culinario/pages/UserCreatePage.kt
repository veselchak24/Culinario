package com.culinario.pages

import android.util.EventLogTags.Description
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.TargetedFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.culinario.mvp.models.User
import com.culinario.viewmodel.UserCreateViewModel
import kotlinx.coroutines.coroutineScope
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
            Text(
                text = "Создание учётной записи",
                modifier = Modifier
                    .padding(25.dp),
                fontSize = 26.sp,
                fontWeight = FontWeight.W600
            )

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
                        0 -> BaseValues(userAvatar, userName)
                        1 -> BaseValues(userAvatar, userName)
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
private fun BaseValues(
    avatarUrl: MutableState<String?>,
    userName: MutableState<String>
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
                }
            )
        }
    }
}

@Composable
private fun Description(
    description: MutableState<String>
) {

}
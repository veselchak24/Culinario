package com.culinario.pages

import android.annotation.SuppressLint
import android.net.Uri
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.culinario.R
import com.culinario.Registration
import com.culinario.controls.Header
import com.culinario.mvp.models.User
import com.culinario.mvp.models.viewmodel.UserPageViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.valentinilk.shimmer.shimmer

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserPage(
    modifier: Modifier = Modifier,
    userId: String,
    navController: NavController
) {
    val userPageViewModel = UserPageViewModel(userId, LocalContext.current)

    val scrollState = rememberScrollState()
    val user = remember { userPageViewModel.getCurrentUser() }
    var accountExitDialog by remember { mutableStateOf(false) }

//    val userRecipes = userPageViewModel.getUserRecipes()

//    val likesCount = userPageViewModel.likesCount().toString()
//    val recipeCount = userPageViewModel.recipeCount().toString()
//    val watchCount = userPageViewModel.watchesCount().toString()

    Scaffold { _ ->
        Box (
            modifier = modifier
                .verticalScroll(scrollState)
        ) {
            BackgroundImageDrawer()

            Column (
                Modifier
                    .padding(horizontal = 25.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                UserHeader(user.value)

                UserAbout(user.value)

                UserStats("0", "0", "0")
//
//                UserActivity (
//                    userRecipes.map {
//                        @Composable {
//                            RecipeCard(RecipePageViewModel(it.id, userPageViewModel.recipeRepository, userPageViewModel.userRepository, LocalContext.current), Modifier.fillMaxWidth(), navController)
//                        }
//                    }
//                )

                if (accountExitDialog) {
                    AlertDialog(
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.alert),
                                contentDescription = "Example Icon"
                            )
                        },
                        title = {
                            Text(
                                text = "Выход",
                                textAlign = TextAlign.Center
                            )
                        },
                        text = {
                            Text(
                                text = "Вы точно хотите выйти с аккаунта ${user.value.name}?",
                                textAlign = TextAlign.Center
                            )
                        },
                        onDismissRequest = {
                            accountExitDialog = false
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    accountExitDialog = false

                                    navController.popBackStack()
                                    navController.navigate(Registration)

                                    Firebase.auth.signOut()
                                }
                            ) {
                                Text(
                                    text = "Да"
                                )
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    accountExitDialog = false
                                }
                            ) {
                                Text(
                                    text = "Нет"
                                )
                            }
                        }
                    )
                }

                if (userPageViewModel.isCurrentUser) {
                    Column {
                        Button (
                            modifier = Modifier
                                .fillMaxWidth(),
                            content = {
                                Text(
                                    text = "Создать рецепт"
                                )
                            },
                            onClick = {
                                navController.navigate(route = "RecipeCreatePage/${user.value.id}")
                            }
                        )

                        TextButton (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp),
                            content = {
                                Text(
                                    text = "Выйти из аккаунта",
                                    color = MaterialTheme.colorScheme.error
                                )
                            },
                            onClick = {
                                accountExitDialog = true
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BackgroundImageDrawer() {
    Image(
        contentDescription = "userBackground",
        painter = painterResource(R.drawable.user_background_placeholder),
        modifier = Modifier
            .height(250.dp)
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
fun UserHeader(user: User) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 15.dp
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        ) {
            Box (
                modifier = Modifier
                    .height(140.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    if (user.name.isEmpty()) {
                        Box (
                            modifier = Modifier
                                .shimmer()
                                .height(28.dp)
                                .width(170.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surface)
                        )
                    } else {
                        Text(
                            text = user.name,
                            fontWeight = FontWeight(900),
                            fontSize = 28.sp
                        )
                    }

                    if (user.email.isEmpty()) {
                        Box (
                            modifier = Modifier
                                .shimmer()
                                .height(20.dp)
                                .width(170.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surface)
                        )
                    } else {
                        Text(
                            text = user.email,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight(200)
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp)
                .width(130.dp)
                .height(130.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            if (user.imageUrl.isNullOrEmpty()) {
                AsyncImage(
                    model = Uri.parse(stringResource(R.string.default_avatar_icon_url)),
                    contentDescription = "userAvatar",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                )
            } else {
                AsyncImage(
                    model = user.imageUrl,
                    contentDescription = "userAvatar",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                )
            }
        }
    }
}

@Composable
fun UserAbout(user: User) {
    if (user.about.isEmpty()) return

    Column {
        Header(stringResource(R.string.user_page_header_about))

        Card (
            elevation = CardDefaults.cardElevation(
                defaultElevation = 15.dp
            ),
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        ) {
            Column (
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(
                    text = user.about,
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

        Row (
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Stat(
                header = stringResource(R.string.user_page_header_likes),
                value = likesCount
            )
            Stat(
                header = stringResource(R.string.user_page_header_recipes_count),
                value = recipeCount
            )
            Stat(
                header = stringResource(R.string.user_page_header_watches_count),
                value = watchCount
            )
        }
    }
}

@Composable
fun UserActivity(composable: List<@Composable () -> Unit>) {
    Column {
        Header(stringResource(R.string.user_page_header_activity))

        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            composable.forEach { composable ->
                composable()
            }
        }
    }
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
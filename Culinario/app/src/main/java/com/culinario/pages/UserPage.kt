package com.culinario.pages

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.culinario.R
import com.culinario.Registration
import com.culinario.controls.Header
import com.culinario.controls.RecipeCard
import com.culinario.controls.ShimmerRecipeCard
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.culinario.viewmodel.RecipeCardViewModel
import com.culinario.viewmodel.UserPageViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserPage(
    modifier: Modifier = Modifier,
    viewModel: UserPageViewModel,
    navController: NavController
) {
    var user by remember { mutableStateOf(viewModel.user.value) }

    LaunchedEffect(Unit) {
        viewModel.user.collect { newState ->
            user = newState
        }
    }

    var accountExitDialog by remember { mutableStateOf(false) }

    Scaffold { _ ->
        Box (
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            BackgroundImagesDrawer(user)

            Column (
                Modifier
                    .padding(horizontal = 25.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                UserHeader(viewModel, user)

                UserAbout(user)

                if (user.recipesId.isNotEmpty()) {
                    UserActivity {
                        Column (
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            for(recipeId in user.recipesId) {
                                RecipeCard(
                                    Modifier.fillMaxWidth(),
                                    RecipeCardViewModel(recipeId)
                                ) {
                                    navController.navigate("RecipePage/${recipeId}")
                                }
                            }
                        }
                    }
                } else if (user.id.isEmpty() && user.recipesId.isEmpty()) {
                    UserStats(viewModel)

                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .shimmer()
                                .clip(RoundedCornerShape(8.dp))
                                .width(120.dp)
                                .height(20.dp)
                                .background(MaterialTheme.colorScheme.surfaceContainer)
                                .padding(horizontal = 10.dp)
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            for(i in 1..3) {
                                ShimmerRecipeCard(
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }


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
                                text = "Вы точно хотите выйти с аккаунта?",
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

                if (viewModel.isCurrentUser) {
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
                                navController.navigate(route = "RecipeCreatePage/${viewModel.userId}")
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
private fun BackgroundImagesDrawer(user: User) {
    AsyncImage(
        model = user.backgroundImageUrl ?: stringResource(R.string.default_background_image_url),
        contentDescription = "userBackground",
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
fun UserHeader(
    viewModel: UserPageViewModel,
    user: User
) {
    val coroutineScope = rememberCoroutineScope()

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        uri?.let {
            coroutineScope.launch {
                viewModel.loadImage(it)
            }
        }
    }

    var isAvatarDialog by remember { mutableStateOf(false) }

    if (isAvatarDialog) {
        Dialog(
            onDismissRequest = {
                isAvatarDialog = false
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerLow)
            ) {
                AsyncImage(
                    model = user.imageUrl,
                    contentDescription = "avatar dialog image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )

                if (viewModel.isCurrentUser) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        onClick = {
                            imagePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        }
                    ) {
                        Text(
                            text = "Выбрать новый аватар"
                        )
                    }
                }
            }
        }
    }

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
                    model = stringResource(R.string.default_avatar_image_url),
                    contentDescription = "userAvatar",
                    modifier = Modifier
                        .shimmer()
                        .fillMaxSize()
                        .padding(5.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                AsyncImage(
                    model = user.imageUrl,
                    contentDescription = "userAvatar",
                    modifier = Modifier
                        .clickable {
                            isAvatarDialog = true
                        }
                        .fillMaxSize()
                        .padding(5.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
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
fun UserStats(viewModel: UserPageViewModel) {
    var recipeCount by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(Unit) {
        recipeCount = viewModel.getRecipesCount()
        println("recipe count handled")
    }

    Column {
        Header(stringResource(R.string.user_page_header_stats))

        if (recipeCount != null) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Stat(
                    header = stringResource(R.string.user_page_header_likes),
                    value = "N/A"
                )
                Stat(
                    header = stringResource(R.string.user_page_header_recipes_count),
                    value = recipeCount.toString()
                )
                Stat(
                    header = stringResource(R.string.user_page_header_watches_count),
                    value = "N/A"
                )
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ShimmerStat()
                ShimmerStat()
                ShimmerStat()
            }
        }
    }
}

@Composable
fun UserActivity(composable: @Composable () -> Unit) {
    Column {
        Header(stringResource(R.string.user_page_header_activity))

        composable()
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

@Composable
fun RowScope.ShimmerStat() {
    Box(
        modifier = Modifier
            .shimmer()
            .weight(1f)
            .padding(horizontal = 5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
    )
}
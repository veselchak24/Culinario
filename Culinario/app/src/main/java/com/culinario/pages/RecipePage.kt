package com.culinario.pages

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.culinario.R
import com.culinario.controls.CommentaryCard
import com.culinario.controls.DetailedStepPageCard
import com.culinario.controls.Header
import com.culinario.controls.IngredientCard
import com.culinario.controls.NutritionInfo
import com.culinario.controls.UserPageLinkButton
import com.culinario.helpers.asColor
import com.culinario.helpers.asWord
import com.culinario.helpers.generateQRCode
import com.culinario.mvp.models.DetailedCookingStep
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.culinario.viewmodel.CommentaryViewModel
import com.culinario.viewmodel.RecipePageViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipePage(
    viewModel: RecipePageViewModel,
    navController: NavController
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    var recipe by remember { mutableStateOf(viewModel.recipe.value) }
    var user by remember { mutableStateOf(viewModel.ownerUser.value) }

    LaunchedEffect(Unit) {
        viewModel.ownerUser.collect { newState ->
            user = newState
        }
    }

    LaunchedEffect(Unit) {
        viewModel.watchedRecipe()
        viewModel.recipe.collect { newState ->
            println("Recipe update")
            recipe = newState
        }
    }

    val isDetailedPageLaunched = remember { mutableStateOf(false) }

    if (isDetailedPageLaunched.value) {
        println("hellow")
        DetailedStepsPage(recipe.steps) {
            isDetailedPageLaunched.value = false
        }
    } else {
        BottomSheetScaffold (
            scaffoldState = rememberBottomSheetScaffoldState(),
            sheetTonalElevation = 10.dp,
            sheetDragHandle = {
                SheetDragHandler()
            },
            sheetPeekHeight = screenHeight / 2,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .clipToBounds(),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    SheetHeader(viewModel, recipe, user, navController)

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp)
                            .padding(top = 10.dp, bottom = 50.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        NutritionInfo(recipe.nutritionInfo)

                        Description(recipe)

                        Ingredients(recipe)

                        DetailedPageButton(recipe, isDetailedPageLaunched)

                        CommentaryField(viewModel)

                        CommentaryList(recipe) {
                            navController.navigate("UserPage/$it")
                        }
                    }
                }
            }
        ) { _ ->
            BackgroundImagesDrawer(
                recipe = recipe,
                screenHeight = screenHeight
            )
        }
    }

}

@Composable
private fun ColumnScope.DetailedPageButton(
    recipe: Recipe,
    isDetailedPageLaunched: MutableState<Boolean>,
) {
    if (recipe.steps.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.primary
                        ),
                        start = Offset(100f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    ),
                    shape = RoundedCornerShape(100)
                )
                .clip(RoundedCornerShape(100))
                .clickable {
                    isDetailedPageLaunched.value = true
                }
                .padding(12.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Text(
                    text = "Начать готовку",
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun BackgroundImagesDrawer(
    recipe: Recipe,
    screenHeight: Dp
) {
    val pagerState = rememberPagerState(0) {
        recipe.recipeImagesUrl.size
    }

    HorizontalPager(
        state = pagerState,
    ) { index ->
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 2 + 50.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = recipe.recipeImagesUrl[index],
                contentDescription = "recipe image",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun SheetDragHandler() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box (
            modifier = Modifier
                .width(30.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.primary)
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun SheetHeader(
    viewModel: RecipePageViewModel,
    recipe: Recipe,
    user: User,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    var isLiked by remember { mutableStateOf(viewModel.isRecipeLiked.value) }

    val isShare = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.isRecipeLiked.collect {
            isLiked = it
        }
    }

    if (isShare.value) {
        ShareDialog(isShare, recipe)
    }

    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = recipe.name,
            maxLines = 2,
            style = MaterialTheme.typography.displaySmall,
            fontFamily = FontFamily.Default
        )

        QuickStats(
            modifier = Modifier
                .padding(horizontal = 5.dp),
            recipe = recipe
        )

        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserPageLinkButton(user.name, user.imageUrl ?: stringResource(R.string.default_avatar_image_url)) {
                navController.navigate("UserPage/${user.id}")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.toggleLike {
                                isLiked = it
                            }
                        }
                    }
                ) {
                    Icon(
                        painter = if (isLiked) painterResource(R.drawable.thumb_up_filled_icon) else painterResource(R.drawable.thumb_up_outlined_icon),
                        contentDescription = "like button",
                        modifier = Modifier
                            .size(22.dp),
                        tint = if (isLiked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }

                Text(
                    text = recipe.otherInfo.likes.toString(),
                    fontWeight = FontWeight.W600
                )
            }

            IconButton(
                onClick = {
                    isShare.value = true
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.share_icon),
                    contentDescription = "share icon",
                    modifier = Modifier
                        .size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun QuickStats(
    modifier: Modifier = Modifier,
    recipe: Recipe = Recipe()
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            IconAndText(
                icon = R.drawable.clock_icon,
                text = "${recipe.cookingSpeed} мин."
            )

            IconAndText(
                icon = R.drawable.fire_icon,
                text = recipe.difficulty.asWord()
            )

            IconAndText(
                icon = R.drawable.spa_icon,
                text = recipe.recipeType.asWord(),
                color = recipe.recipeType.asColor()
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            IconAndText(
                icon = R.drawable.weight_icon,
                text = "${recipe.totalWeight} г."
            )

            IconAndText(
                icon = R.drawable.watches_icon,
                text = recipe.otherInfo.watches.toString()
            )
        }
    }
}

@Composable
private fun IconAndText(
    @DrawableRes icon: Int,
    text: String,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "clock icon",
            modifier = Modifier
                .size(16.dp),
            tint = color
        )

        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}


@Composable
private fun Description(recipe: Recipe) {
    if (recipe.description.isEmpty()) return

    Column {
        Header(stringResource(R.string.recipe_page_header_description))

        Text (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = recipe.description,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
private fun Ingredients(recipe: Recipe) {
    if (recipe.ingredients.isEmpty()) return

    Column {
        Header(stringResource(R.string.recipe_page_header_ingredients))

        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            recipe.ingredients.forEach { ingredient ->
                IngredientCard(ingredient)
            }
        }
    }
}

@Composable
private fun DetailedStepsPage(
    steps: List<DetailedCookingStep>,
    onClose: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(0) { steps.size }

    BackHandler {
        if (pagerState.currentPage > 0) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        } else {
            onClose()
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxHeight(.9f)
        ) { index ->
            DetailedStepPageCard(Modifier, steps[index])
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    enabled = pagerState.currentPage > 0
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_back_icon),
                        contentDescription = "back arrow",
                        tint = if (pagerState.currentPage > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainer
                    )
                }

                Text(
                    text = "${pagerState.currentPage + 1} из ${steps.size}",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    enabled = pagerState.currentPage + 1 < steps.size
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_forward_icon),
                        contentDescription = "forward arrow",
                        tint = if (pagerState.currentPage + 1 < steps.size) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainer
                    )
                }
            }

            if (pagerState.currentPage == steps.size - 1) {
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                    onClick = {
                        onClose()
                    }
                ) {
                    Text(
                        text = "Готово!"
                    )
                }
            }
        }
    }
}

@Composable
private fun ShareDialog(
    isShare: MutableState<Boolean>,
    recipe: Recipe
) {
    Dialog(
        onDismissRequest = {
            isShare.value = false
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .wrapContentHeight()
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            val qrBitmap = generateQRCode("culinario://recipe-page/${recipe.id}")

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(15.dp)),
                bitmap = qrBitmap.asImageBitmap(),
                contentDescription = "share qr code"
            )

            Text(
                text = recipe.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 10.dp)
            )

            Text(
                text = "Отсканируйте qr, чтобы поделиться рецептом",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
        }
    }
}

@Composable
fun CommentaryField(
    viewModel: RecipePageViewModel
) {
    val commentaryText = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val isFieldEnabled = remember { mutableStateOf(true) }

    Column {
        Header("Комментарии")

        TextField(
            value = commentaryText.value,
            onValueChange = {
                commentaryText.value = it
            },
            enabled = isFieldEnabled.value,
            placeholder = {
                Text(
                    text = "комментарий.."
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            suffix = {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd),
                        onClick = {
                            sendCommentary(viewModel, commentaryText, isFieldEnabled, coroutineScope)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.send_icon),
                            contentDescription = "send icon"
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun CommentaryList(
    recipe: Recipe,
    onUserClicked: (id: String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        recipe.commentaries.forEach { commentaryId ->
            CommentaryCard(CommentaryViewModel(commentaryId)) { userId ->
                onUserClicked(userId)
            }
        }
    }
}

private fun sendCommentary(
    viewModel: RecipePageViewModel,
    commentaryText: MutableState<String>,
    isFieldEnabled: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    onSent: () -> Unit = { }
) {
    if (commentaryText.value.isEmpty()) return

    isFieldEnabled.value = false

    val text = commentaryText.value
    commentaryText.value = ""

    coroutineScope.launch {
        viewModel.sendCommentary(text) {
            isFieldEnabled.value = true

            onSent()
        }
    }
}
package com.culinario.pages

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.culinario.R
import com.culinario.controls.Header
import com.culinario.controls.IngredientCard
import com.culinario.controls.NutritionInfo
import com.culinario.mvp.models.Recipe
import com.culinario.mvp.models.User
import com.culinario.viewmodel.IngredientCardViewModel
import com.culinario.viewmodel.RecipePageViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipePage(
    viewModel: RecipePageViewModel,
    navController: NavController
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    var recipe by remember { mutableStateOf(viewModel.recipeState.value) }
    var user by remember { mutableStateOf(viewModel.userState.value) }

    LaunchedEffect(Unit) {
        viewModel.userState.collect { newState ->
            user = newState
        }
    }

    LaunchedEffect(Unit) {
        viewModel.watchedRecipe()
        viewModel.recipeState.collect { newState ->
            println("Recipe update")
            recipe = newState
        }
    }

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

                    Steps(recipe)

                    Commentaries(viewModel)
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

    LaunchedEffect(Unit) {
        viewModel.isRecipeLiked.collect {
            isLiked = it
        }
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
            BasicUserData(user) {
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
                    Row {
                        Icon(
                            painter = if (isLiked) painterResource(R.drawable.thumb_up_filled_icon) else painterResource(R.drawable.thumb_up_outlined_icon),
                            contentDescription = "like button",
                            modifier = Modifier
                                .size(22.dp),
                            tint = if (isLiked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Text(
                    text = recipe.otherInfo.likes.toString(),
                    fontWeight = FontWeight.W600
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
                text = recipe.difficulty.toString()
            )

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
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "clock icon",
            modifier = Modifier
                .size(14.dp),
            tint = MaterialTheme.colorScheme.primary
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
            recipe.ingredients.forEach { ingredientId ->
                IngredientCard(IngredientCardViewModel(ingredientId))
            }
        }
    }
}

@Composable
private fun Steps(recipe: Recipe) {
    if (recipe.steps.isEmpty()) return

    Column {
        Header(stringResource(R.string.recipe_page_header_steps))

        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(15.dp)
            ) {
                recipe.steps.forEach { step ->
                    Text(text = step.description)
                }
            }
        }
    }
}

@Composable
fun BasicUserData(user: User, onClick: () -> Unit) {
    Row (
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .clickable {
                onClick()
            }
            .padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.imageUrl,
            contentDescription = "user avatar",
            modifier = Modifier
                .clip(CircleShape)
                .size(30.dp),
            contentScale = ContentScale.Crop
        )
        Text (
            text = user.name
        )
    }
}

@Composable
fun Commentaries(
    viewModel: RecipePageViewModel
) {
    var myCommentary by remember { mutableStateOf("") }

    Column {
        Header("Комментарии")

        TextField(
            value = myCommentary,
            onValueChange = {
                myCommentary = it
            },
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
                            //TODO: send commentary
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
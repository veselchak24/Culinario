package com.culinario.pages

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culinario.R
import com.culinario.controls.Header
import com.culinario.mvp.models.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipePage(recipe: Recipe, modifier: Modifier = Modifier) {
    val sheetPeekHeight = LocalConfiguration.current.screenHeightDp.dp
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold (
        scaffoldState = scaffoldState,
        sheetTonalElevation = 10.dp,
        sheetDragHandle = {
            SheetDragHandler()
        },
        sheetPeekHeight = sheetPeekHeight / 2 + 150.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .clipToBounds(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                SheetHeader(recipe)

                Column (
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .padding(top = 10.dp, bottom = 50.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Description(recipe)
                    QuickStats(recipe)
                    ImageCarousel(recipe)
                    Ingredients(recipe)
                    Steps(recipe)
                }
            }
        }
    ) { innerPadding ->
        BackgroundImage(innerPadding, modifier, sheetPeekHeight)
    }
}

@Composable
private fun BackgroundImage(
    innerPadding: PaddingValues,
    modifier: Modifier,
    sheetPeekHeight: Dp
) {
    Column(
        modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(R.drawable.recipe_page_bg),
            modifier = modifier
                .fillMaxWidth()
                .height(sheetPeekHeight / 2),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
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
        Box(
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
private fun SheetHeader(recipe: Recipe) {
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = recipe.name,
            maxLines = 2,
            style = MaterialTheme.typography.displaySmall,
            fontFamily = FontFamily.Default
        )

        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            IconAndText(recipe.author.name, R.drawable.round_person_outline_24, 18, 16)

            VerticalDivider(thickness = 1.dp, modifier = Modifier.padding(vertical = 3.dp))

            Text(
                text = recipe.author.email ?: "artur-pirozhkoff",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun Description(recipe: Recipe) {
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
private fun QuickStats(recipe: Recipe) {
    Column {
        Header(stringResource(R.string.recipe_page_header_summary))

        Card {
            Column (
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(20.dp)
            ) {
                Text(text = "Время приготовления ~${recipe.cookingSpeed}мин", fontWeight = FontWeight.Light)
                Text(text = "Сложность блюда: ${recipe.difficulty}", fontWeight = FontWeight.Light)
                Text(text = "Тип блюда: ${recipe.recipeType}", fontWeight = FontWeight.Light)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCarousel(recipe: Recipe) {
    val carouselState = rememberCarouselState { 3 }

    Column {
        Header(stringResource(R.string.recipe_page_header_pictures))

        Box(
            modifier = Modifier.height(250.dp),
            contentAlignment = Alignment.Center
        ) {

            HorizontalMultiBrowseCarousel (
                state = carouselState,
                preferredItemWidth = 300.dp,
                itemSpacing = 10.dp
            ) { page ->
                Box (
                    modifier = Modifier.width(300.dp)
                ) {
                    Box (
                        modifier = Modifier
                            .maskClip(RoundedCornerShape(16.dp))
                    ) {
                        Image (
                            modifier = Modifier
                                .fillMaxSize(),
                            painter = painterResource (
                                id = when (page) {
                                    0 -> R.drawable.pasta
                                    1 -> R.drawable.pasta2
                                    else -> R.drawable.pasta3
                                }
                            ),
                            contentDescription = "recipe image",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Ingredients(recipe: Recipe) {
    Column {
        Header(stringResource(R.string.recipe_page_header_ingredients))

        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(15.dp)
            ) {
                recipe.ingredients.forEach { ingredient ->
                    Text(text = ingredient.name)
                }
            }
        }
    }
}

@Composable
private fun Steps(recipe: Recipe) {
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
                    Text(text = step)
                }
            }
        }
    }
}

@Preview
@Composable
fun IconAndText(
    message: String = "",
    @DrawableRes iconId: Int = R.drawable.round_access_time_24,
    textSize: Int = 18,
    iconSize: Int = 18,
    textStyle: TextStyle = TextStyle()
) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon (
            painterResource(iconId),
            "icon",
            modifier = Modifier.size(iconSize.dp + 5.dp, iconSize.dp + 5.dp)
        )

        Text(
            text = message,
            modifier = Modifier
                .padding(start = 5.dp),
            textAlign = TextAlign.Center,
            fontSize = textSize.sp,
            style = textStyle
        )
    }
}
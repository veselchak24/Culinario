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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culinario.R
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
                    Description()
                    QuickStats(recipe)
                    ImageCarousel(Modifier)
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
private fun Description() {
    Column {
        Header(stringResource(R.string.recipe_page_header_description))

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
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = LoremIpsum().values.first(),
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
private fun QuickStats(recipe: Recipe) {
    Column(
        Modifier.padding(start = 5.dp)
    ) {
        Text(text = "Время приготовления ~${recipe.cookingSpeed}мин")
        Text(text = "Сложность блюда: ${recipe.difficulty}")
        Text(text = "Тип блюда: ${recipe.recipeType}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCarousel(modifier: Modifier) {
    val carouselState = rememberCarouselState { 3 }

    Box(
        modifier = Modifier.height(250.dp),
        contentAlignment = Alignment.Center
    ) {

        HorizontalMultiBrowseCarousel(
            state = carouselState,
            preferredItemWidth = 300.dp,
            itemSpacing = 10.dp,
            modifier = modifier
        ) { page ->
            Box (
                modifier = Modifier.width(300.dp)
            ){
                Box (
                    modifier = Modifier
                        .maskClip(RoundedCornerShape(16.dp))
                ) {
                    Image(painter = painterResource(
                        id = when (page) {
                            0 -> R.drawable.pasta
                            1 -> R.drawable.pasta2
                            else -> R.drawable.pasta3
                        }),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()

                    )

                    Text(
                        text = "test",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun Ingredients(recipe: Recipe) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Ингредиенты:",
                modifier = Modifier
                    .padding(end = 10.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(10.dp))

            recipe.ingredients.forEach { ingredient ->
                Text(text = ingredient.name)
            }
        }
    }
}

@Composable
private fun Steps(recipe: Recipe) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Шаги приготовления:",
                modifier = Modifier
                    .padding(end = 10.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(10.dp))

            recipe.steps.forEach { step ->
                Text(text = step)
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
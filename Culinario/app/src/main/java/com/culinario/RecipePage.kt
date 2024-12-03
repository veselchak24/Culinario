package com.culinario

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culinario.mvp.models.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipePage(recipe: Recipe, modifier: Modifier = Modifier) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetTonalElevation = 10.dp,
        sheetPeekHeight = screenHeight / 2 + 150.dp,

        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Column (
                    Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(start = 30.dp, top = 30.dp, end = 30.dp, bottom = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = recipe.name,
                        modifier = Modifier,
                        style = MaterialTheme.typography.displayLarge,
                        fontFamily = FontFamily.Default
                    )
                    Row (
                        modifier = Modifier.height(IntrinsicSize.Min),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        IconAndText(recipe.author.name, R.drawable.round_person_outline_24, 20)

                        VerticalDivider(thickness = 1.dp, modifier = Modifier.padding(top = 3.dp, bottom = 3.dp))
                        Text(
                            text = recipe.author.email ?: "artur-pirozhkoff",
                            modifier = Modifier.padding(),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }

                Column (
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 150.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(5.dp),
                        text = stringResource(R.string.lorem_ipsum),
                        fontWeight = FontWeight.Light
                    )

                    Column (
                        Modifier.padding(start = 5.dp)
                    ) {
                        Text(text = "Время приготовления ~${recipe.cookingSpeed}мин")
                        Text(text = "Сложность блюда: ${recipe.difficulty}")
                        Text(text = "Тип блюда: ${recipe.recipeType}")
                    }

                    MyCarousel(Modifier)

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
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.recipe_page_bg),
                modifier = modifier
                    .fillMaxWidth()
                    .height(screenHeight / 2),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun IconAndText(
    message: String = "25 min",
    @DrawableRes iconId: Int = R.drawable.round_access_time_24,
    textAndIconSize: Int = 16,
    textStyle: TextStyle = TextStyle()
) {
    Row {
        Icon(
            painterResource(iconId),
            null,
            modifier = Modifier.size(textAndIconSize.dp + 5.dp, textAndIconSize.dp + 5.dp)
        )

        Text(
            text = message,
            modifier = Modifier
                .padding(start = 5.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Default,
            fontSize = textAndIconSize.sp,
            style = textStyle
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCarousel(modifier: Modifier) {
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
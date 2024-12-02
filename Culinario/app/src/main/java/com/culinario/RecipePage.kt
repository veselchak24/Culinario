package com.culinario

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culinario.mvp.models.Recipe
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipePage(recipe: Recipe, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            modifier = modifier
                .padding(start = 15.dp, end = 15.dp, bottom = 0.dp)
                .height(IntrinsicSize.Min)
                .clickable {

                }
        ) {
            Column(
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    painter = painterResource(id = R.drawable.recipe_page_bg),
                    contentDescription = "PastaImage"
                )
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.Left,
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                    ) {
                        Text(
                            text = recipe.name,
                            modifier = Modifier
                                .padding(end = 10.dp),
                            style = MaterialTheme.typography.displaySmall,
                            fontFamily = FontFamily.Default
                        )
                        VerticalDivider(
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                        Text(
                            text = "Автор: ${recipe.author.name}",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Время приготовления ~${recipe.cookingSpeed}мин",
                        fontSize = 16.sp
                    )

                    Text(
                        text = "Сложность блюда: ${recipe.difficulty}",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Тип блюда: ${recipe.recipeType}",
                        fontSize = 16.sp
                    )
                }
            }
        }


        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Ингредиенты",
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
                .padding(start = 15.dp, end = 15.dp)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Шаги приготовления",
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
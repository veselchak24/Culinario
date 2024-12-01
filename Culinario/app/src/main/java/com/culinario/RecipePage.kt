package com.culinario

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culinario.mvp.models.Recipe

@Composable
fun RecipePage(recipe: Recipe, modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
        ) {
            Column (
                verticalArrangement = Arrangement.Top
            )  {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.recipe_page_bg),
                    contentDescription = "PastaImage"
                )
                Column (
                    modifier = Modifier.padding(15.dp)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.Left,
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                    ) {
                        Text(
                            text = recipe.name,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(end = 10.dp)
                        )
                        VerticalDivider(color = MaterialTheme.colorScheme.secondary, modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
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
                        text = "Сложность блюда: ${recipe.difficulty}мин",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Тип блюда: ${recipe.recipeType}мин",
                        fontSize = 16.sp
                    )
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
        ) {
            Column (
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Ингредиенты:",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                recipe.ingredients.forEach { ingredient ->
                    Text(text = ingredient.name)
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
        ) {
            Column (
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Шаги приготовления:",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                recipe.steps.forEach { step ->
                    Text(text = step)
                }
            }
        }
    }
}
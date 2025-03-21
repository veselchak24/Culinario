package com.culinario.controls

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.culinario.R
import com.culinario.mvp.models.Recipe

@Composable
fun RecipeCard(recipe: Recipe? = null, modifier: Modifier) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        modifier = modifier
            .height(250.dp)
            .width(200.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        var isClick by remember {
            mutableStateOf(false)
        }

        if(isClick) {
            //TODO: "прикрутить сюда переход на страницу рецептов"
            //RecipePage(recipe!!, modifier)
        }

        Column (
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    isClick = true
                }
        ){
            Image(
                modifier = Modifier
                    .weight(0.7f),
                painter = painterResource(R.drawable.pasta),
                contentScale = ContentScale.Crop,
                contentDescription = "idk"
            )

            Column (
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxSize()
                    .padding(10.dp)
            ){
                Text(
                    text = recipe?.name ?: "Рецепт",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )

                Row {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "author",
                        Modifier
                            .size(15.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Text(
                        modifier = Modifier.padding(start = 3.dp),
                        text = recipe?.author?.name ?: "Автор",
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1
                    )

                    Text(
                        text = R.string.lorem_ipsum.toString(),
                        maxLines = 2,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
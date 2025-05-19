package com.culinario.controls

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.culinario.R
import com.culinario.mvp.models.User
import com.culinario.viewmodel.RecipeCardViewModel
import com.culinario.viewmodels.RecipePageViewModel

@Composable
fun RecipeCard(
    recipeCardViewModel: RecipeCardViewModel,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val recipe = recipeCardViewModel.recipe.collectAsState()
    var userOwner by remember { mutableStateOf(User()) }

    var clicked by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        userOwner = recipeCardViewModel.getOwner()
    }

    LaunchedEffect(clicked) {
        if (clicked) {
            onClick()
        }
    }

    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        modifier = modifier
            .height(250.dp)
            .width(200.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    clicked = true
                }
        ) {
            AsyncImage(
                modifier = Modifier
                    .weight(0.7f),
                model = recipe.value.recipeImageResources.recipeBackgroundImageResources,
                contentScale = ContentScale.Crop,
                contentDescription = "idk"
            )

            Column (
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text (
                    text = recipe.value.name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    modifier = Modifier
                        .basicMarquee()
                )

                Row (
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image (
                        painter = painterResource(R.drawable.user_avatar_placeholder),
                        contentDescription = "author",
                        Modifier
                            .size(20.dp)
                            .align(Alignment.CenterVertically)
                            .clip(CircleShape)
                    )
                    Text(
                        modifier = Modifier.padding(start = 3.dp),
                        text = userOwner.name,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
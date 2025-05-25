package com.culinario.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.culinario.R
import com.culinario.mvp.models.Commentary
import com.culinario.mvp.models.User
import com.culinario.viewmodel.CommentaryViewModel
import kotlinx.coroutines.launch

@Composable
fun CommentaryCard(
    viewModel: CommentaryViewModel,
    onUserClicked: (id: String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    var ownerUser by remember { mutableStateOf(User()) }
    var commentary by remember { mutableStateOf(Commentary()) }
    var isLiked by remember { mutableStateOf(viewModel.isLiked.value) }

    LaunchedEffect(Unit) {
        viewModel.ownerUser.collect { newState ->
            ownerUser = newState
        }
    }

    LaunchedEffect(Unit) {
        viewModel.commentary.collect { newState ->
            commentary = newState
        }
    }

    LaunchedEffect(Unit) {
        viewModel.isLiked.collect { newState ->
            isLiked = newState
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            MiniUserPageLinkButton(
                ownerUser.name,
                ownerUser.imageUrl ?: stringResource(R.string.default_avatar_image_url)
            ) {
                onUserClicked(ownerUser.id)
            }

            Text(
                text = commentary.date,
                fontWeight = FontWeight.W300,
                fontSize = 12.sp
            )
        }

        Text(
            text = commentary.text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )

        IconButton(
            modifier = Modifier
                .padding(0.dp),
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
                    .size(15.dp),
                tint = if (isLiked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun MiniUserPageLinkButton(
    name: String,
    imageUrl: String,
    onClick: () -> Unit
) {
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
            model = imageUrl,
            contentDescription = "user avatar",
            modifier = Modifier
                .clip(CircleShape)
                .size(20.dp),
            contentScale = ContentScale.Crop
        )

        Text (
            text = name
        )
    }
}
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.culinario.R
import com.culinario.helpers.timeSince
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

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        AsyncImage(
            model = ownerUser.imageUrl ?: stringResource(R.string.default_avatar_image_url),
            contentDescription = "user avatar",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 5.dp)
                .align(Alignment.Top)
                .size(30.dp)
                .clip(CircleShape)
                .clickable {
                    onUserClicked(ownerUser.id)
                }
        )

        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ownerUser.name,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .clickable {
                            onUserClicked(ownerUser.id)
                        }
                        .padding(2.dp)
                )

                Text(
                    text = timeSince(commentary.timeStamp),
                    fontWeight = FontWeight.W300,
                    fontSize = 12.sp
                )
            }

            Text(
                text = commentary.text,
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = commentary.likes.toString(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center
                )

                Icon(
                    painter = if (isLiked) painterResource(R.drawable.thumb_up_filled_icon) else painterResource(R.drawable.thumb_up_outlined_icon),
                    tint = if (isLiked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                    contentDescription = "like button",
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            coroutineScope.launch {
                                viewModel.toggleLike {
                                    isLiked = it
                                }
                            }
                        }
                        .padding(3.dp)
                        .size(15.dp)
                )
            }
        }
    }
}
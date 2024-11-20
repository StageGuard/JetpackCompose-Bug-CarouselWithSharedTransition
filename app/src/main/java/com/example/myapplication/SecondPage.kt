package com.example.myapplication

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SecondPage(
    imageUrl: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onGoBack: () -> Unit,
) {
    val currentImageUrl by rememberUpdatedState(imageUrl)

    Column {
        Button(onClick = onGoBack) { Text("Go Back") }
        Spacer(Modifier.fillMaxWidth().height(16.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(currentImageUrl)
                .build(),
            modifier = Modifier
                .sharedElement(
                    rememberSharedContentState(SharedTransitionKey.IMAGE),
                    animatedVisibilityScope
                )
                .clip(MaterialTheme.shapes.medium),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
package com.example.myapplication

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

private val imageData = listOf(
    "https://raw.githubusercontent.com/Aikoyori/ProgrammingVTuberLogos/refs/heads/main/osu/osuLogoShadowed.png",
    "https://raw.githubusercontent.com/Aikoyori/ProgrammingVTuberLogos/refs/heads/main/VSCode/VSCode-Thick.png",
    "https://raw.githubusercontent.com/Aikoyori/ProgrammingVTuberLogos/refs/heads/main/PaperMC/PaperMCLogoShadowed.png",
    "https://raw.githubusercontent.com/Aikoyori/ProgrammingVTuberLogos/refs/heads/main/Neovim/NeovimShadowed.png",
    "https://raw.githubusercontent.com/Aikoyori/ProgrammingVTuberLogos/refs/heads/main/Kubernetes/kubernetesLogoShadow.png",
    "https://raw.githubusercontent.com/Aikoyori/ProgrammingVTuberLogos/refs/heads/main/IntelliJIDEA/intelliJShadow.png",
    "https://raw.githubusercontent.com/Aikoyori/ProgrammingVTuberLogos/refs/heads/main/Docker/DockerLogoShadowed.png",
    "https://raw.githubusercontent.com/Aikoyori/ProgrammingVTuberLogos/refs/heads/main/ImHex/ImHexLogoSVGBGShadows.png",
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MainPage(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClickImage: (String) -> Unit,
) {
    val state = rememberCarouselState(0) { 8 }
    HorizontalMultiBrowseCarousel(
        state,
        preferredItemWidth = 240.dp,
        itemSpacing = 16.dp,
        flingBehavior = CarouselDefaults.multiBrowseFlingBehavior(
            state,
            snapAnimationSpec = spring(stiffness = Spring.StiffnessMedium),
        ),
    ) { index ->
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageData[index])
                .crossfade(true)
                .build(),
            modifier = Modifier
                .sharedElement(
                    rememberSharedContentState(SharedTransitionKey.IMAGE),
                    animatedVisibilityScope
                )
                .height(213.dp)
                .clickable { onClickImage(imageData[index]) },
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
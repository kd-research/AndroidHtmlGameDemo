package com.gamecraft.htmlgameshow.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gamecraft.htmlgameshow.data.Game
import com.gamecraft.htmlgameshow.viewmodel.GameIndexViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun HtmlSubscene(
    game: Game,
    navigator: ThreePaneScaffoldNavigator<Game>,
    viewModel: GameIndexViewModel,
    modifier: Modifier = Modifier
) {
    var showSplash by remember { mutableStateOf(true) }
    val alphaAnim = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        alphaAnim.animateTo(targetValue = 1f, animationSpec = tween(1000))
        delay(1000)
        showSplash = false
    }

    if (showSplash) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier
                    .size(width = 160.dp, height = 160.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .graphicsLayer { alpha = alphaAnim.value },
                model = "file:///android_asset/icons/" + game.icon,
                contentDescription = game.toString(),
                contentScale = ContentScale.Fit
            )
        }
    }
    HtmlRender(
            game = game,
            navigator = navigator,
            viewModel = viewModel,
            modifier = modifier.alpha(if (showSplash) 0f else 1f)
    )

}

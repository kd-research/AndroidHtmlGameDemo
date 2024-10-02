package com.gamecraft.htmlgameshow.view

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gamecraft.htmlgameshow.R

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListingGamesByGrid(
    scaffoldNavigator: ThreePaneScaffoldNavigator<String>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyGridState()
    val displayImages = mapOf(
        "wordsnake" to R.drawable.lettersnake,
        "numerictowers" to R.drawable.numerictower,
        "mathdash" to R.drawable.mathdash,
        "retrowordtetris" to R.drawable.wordtetris,
        "wordcollapse" to R.drawable.wordcollapse,
        "letterloft" to R.drawable.letterloft
    )
    val targets = displayImages.keys.toList()
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(180.dp), state = listState) {
        items(targets.size) {
            // In case we want do repeat targets to demo ability to show more games
            val target = targets[it % targets.size]
            val imageId = displayImages.getOrDefault(target, R.drawable.default_thumbnail)
            ListingGameByGridItem(target, scaffoldNavigator, thumbnailImage = imageId)
        }
    }
}
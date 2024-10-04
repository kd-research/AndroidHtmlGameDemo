package com.example.htmlgameshow.view

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.htmlgameshow.R

internal val headlines = listOf("Number Tower", "Word Tetris", "Letter Snake", "Math Dash", "LetterLoft Balloons")
internal val targets = listOf("numerictowers", "retrowordtetris", "wordsnake", "mathdash", "letterloftballoons")
internal val displayImages = mapOf(
    "numerictowers" to R.drawable.numerictower,
    "retrowordtetris" to R.drawable.wordtetris,
    "wordsnake" to R.drawable.lettersnake,
    "mathdash" to R.drawable.mathdash,
    "letterloftballoons" to R.drawable.letterloftballoons
)

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListingGamesByGrid(
    scaffoldNavigator: ThreePaneScaffoldNavigator<String>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyGridState()

    LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(180.dp), state = listState) {
        items(targets.size) {
            val target = targets.get(it % targets.size)
            val headline = headlines.get(it % headlines.size)
            val imageId = displayImages.getOrDefault(target, R.drawable.default_thumbnail)
            ListingGameByGridItem(target, scaffoldNavigator, thumbnailImage = imageId, headline)
        }
    }
}
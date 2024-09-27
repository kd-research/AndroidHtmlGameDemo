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

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListingGamesByGrid(
    scaffoldNavigator: ThreePaneScaffoldNavigator<String>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyGridState()
    val targets = listOf("newwordle", "sudokudetective", "equationexplorer", "cipherchronicles", "numerictowers", "lettersnake", "wordtetris", "wordwheels", "lexiconlegends")
    val displayImages = mapOf(
        "wordtetris" to R.drawable.wordtetris,
        "lettersnake" to R.drawable.lettersnake,
        "numerictowers" to R.drawable.numerictower,
        "mathdash" to R.drawable.mathdash
    ) + mapOf(
        "sudokudetective" to R.drawable.lettersnake,
        "newwordle" to R.drawable.wordtetris,
        "equationexplorer" to R.drawable.mathdash
    )
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(180.dp), state = listState) {
        items(targets.size) {
            val target = targets.get(it % targets.size)
            val imageId = displayImages.getOrDefault(target, R.drawable.default_thumbnail)
            ListingGameByGridItem(target, scaffoldNavigator, thumbnailImage = imageId)
        }
    }
}
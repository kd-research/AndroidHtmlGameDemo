package com.gamecraft.htmlgameshow.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gamecraft.htmlgameshow.data.Game
import com.gamecraft.htmlgameshow.viewmodel.GameIndexViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListingGamesByGrid(
    scaffoldNavigator: ThreePaneScaffoldNavigator<Game>,
    viewModel: GameIndexViewModel,
    modifier: Modifier = Modifier,
) {
    val lazyGridState = rememberLazyGridState()

    LazyVerticalGrid(
        contentPadding = PaddingValues(vertical = 60.dp),
        modifier = modifier,
        columns = GridCells.Adaptive(180.dp),
        state = lazyGridState
    ) {
        items(viewModel.games) { game ->
            ListingGameByGridItem(game,
                onFavouriteClick = { viewModel.toggleFavourite(game) },
                onItemClick = {
                    scaffoldNavigator.navigateTo(
                        ListDetailPaneScaffoldRole.Detail,
                        game
                    )
                })
        }
    }
}
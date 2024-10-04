package com.gamecraft.htmlgameshow.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gamecraft.htmlgameshow.R
import com.gamecraft.htmlgameshow.data.Game
import com.gamecraft.htmlgameshow.data.GameDatabase
import com.gamecraft.htmlgameshow.viewmodel.GameIndexViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailPane(viewModel: GameIndexViewModel = GameIndexViewModel()) {
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<Game>()

    ListDetailPaneScaffold(
        directive = scaffoldNavigator.scaffoldDirective,
        value = scaffoldNavigator.scaffoldValue,
        listPane = {
            AnimatedPane(
                modifier = Modifier
                    .paint(
                        painterResource(R.drawable.background),
                        contentScale = ContentScale.Crop
                    )
            ) {
                ListingGamesByGrid(scaffoldNavigator, viewModel, modifier = Modifier)
                Box {
                    val context = LocalContext.current
                    IconButton(onClick = { viewModel.resetDb(context) }) {
                        Icon(Icons.Outlined.Settings, "Settings")
                    }
                }
            }
        },
        detailPane = {
            AnimatedPane {
                scaffoldNavigator.currentDestination?.content?.let {
                    HtmlRender(it, scaffoldNavigator, viewModel)
                }
            }
        }
    )
}



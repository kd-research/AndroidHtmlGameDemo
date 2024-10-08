package com.gamecraft.htmlgameshow.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamecraft.htmlgameshow.R
import com.gamecraft.htmlgameshow.data.Game
import com.gamecraft.htmlgameshow.viewmodel.GameIndexViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailPane(viewModel: GameIndexViewModel = GameIndexViewModel()) {
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<Game>()

    ListDetailPaneScaffold(directive = scaffoldNavigator.scaffoldDirective,
        value = scaffoldNavigator.scaffoldValue,
        listPane = {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.background),
                    contentDescription = "Background",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                )

                Scaffold(
                    containerColor = Color.Transparent,
                ) {
                    ListingGamesByGrid(scaffoldNavigator, viewModel, modifier = Modifier)
                    Box {
                        FilledTonalButton(
                            onClick = { viewModel.resetHighScores() },
                            modifier = Modifier.absoluteOffset(20.dp, 10.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp)
                        ) {
                            Text("Reset High Score", fontSize = 12.sp)
                        }
                    }
                }
            }
        },
        detailPane = {
            Scaffold {
                scaffoldNavigator.currentDestination?.content?.let {
                    HtmlSubscene(it, scaffoldNavigator, viewModel)
                }
            }
        })
}



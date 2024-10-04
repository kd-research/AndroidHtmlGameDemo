package com.gamecraft.htmlgameshow.view

import android.webkit.WebView
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gamecraft.htmlgameshow.data.Game

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun TopBarGames(
    scaffoldNavigator: ThreePaneScaffoldNavigator<Game>,
    closeWebView: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (scaffoldNavigator.canNavigateBack())
        FloatingActionButton(
            onClick = {
                scaffoldNavigator.navigateBack()
                closeWebView()
                      },
            containerColor = Color.LightGray,
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(5.dp),
            modifier = modifier
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Navigate Back"
            )
        }

}
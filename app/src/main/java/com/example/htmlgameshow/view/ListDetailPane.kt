package com.example.htmlgameshow.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.htmlgameshow.R

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailPane() {
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<String>()

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
                ListingGamesByGrid(scaffoldNavigator, modifier = Modifier.padding(top = 100.dp))
            }
        },
        detailPane = {
            AnimatedPane {
                scaffoldNavigator.currentDestination?.content?.let {
                    HtmlRender(it)
                }
            }
        }
    )
    TopBarGames2(scaffoldNavigator, modifier = Modifier.absoluteOffset(x = 10.dp, y = 30.dp))

}


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun TopBarGames2(
    scaffoldNavigator: ThreePaneScaffoldNavigator<String>,
    modifier: Modifier = Modifier
) {
    if (scaffoldNavigator.canNavigateBack())
        FloatingActionButton(
            onClick = { scaffoldNavigator.navigateBack() },
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
    else
        FloatingActionButton(
            modifier = modifier,
            onClick = {
                scaffoldNavigator.navigateTo(
                    ListDetailPaneScaffoldRole.Detail,
                    "_settings"
                )

            },
            elevation = FloatingActionButtonDefaults.elevation(0.dp),

            containerColor = Color.Transparent

        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Settings"
            )
        }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
private fun TopBarGames(scaffoldNavigator: ThreePaneScaffoldNavigator<String>) {
    CenterAlignedTopAppBar(
        title = { Text("\nGames", maxLines = 2) },
        modifier = Modifier.height(100.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            if (scaffoldNavigator.canNavigateBack())
                IconButton(onClick = { scaffoldNavigator.navigateBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigate Back"
                    )
                }
            else
                IconButton(onClick = {
                    scaffoldNavigator.navigateTo(
                        ListDetailPaneScaffoldRole.Detail,
                        "_settings"
                    )
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Settings"
                    )
                }
        },
    )
}


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ListingGames(
    scaffoldNavigator: ThreePaneScaffoldNavigator<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        item {
            ListGameItem("Numeric Towers", "numerictowers", scaffoldNavigator)
        }
        item {
            ListGameItem("Retro Word Tetris", "retrowordtetris", scaffoldNavigator)
        }
        item {
            ListGameItem("Letter Snake", "wordsnake", scaffoldNavigator)
        }
        item {
            ListGameItem("Math Dash", "mathdash", scaffoldNavigator)
        }
        item {
            ListGameItem("Word Collapse", "wordcollapse", scaffoldNavigator)
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ListGameItem(
    headline: String,
    target: String,
    scaffoldNavigator: ThreePaneScaffoldNavigator<String>
) {
    ListItem(
        headlineContent = {
            Text(headline)
        },
        modifier = Modifier.clickable {
            scaffoldNavigator.navigateTo(
                ListDetailPaneScaffoldRole.Detail,
                target
            )
        }
    )
}
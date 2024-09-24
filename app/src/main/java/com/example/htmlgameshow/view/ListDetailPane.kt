package com.example.htmlgameshow.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailPane() {
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<String>()
    Scaffold(
        topBar = {
            TopBarGames(scaffoldNavigator)
        },
        content = {
            ListDetailPaneScaffold(
                directive = scaffoldNavigator.scaffoldDirective,
                value = scaffoldNavigator.scaffoldValue,
                listPane = {
                    AnimatedPane(modifier = Modifier.padding(it)) {
                        ListingGames(scaffoldNavigator)
                    }
                },
                detailPane = {
                    AnimatedPane(modifier = Modifier.padding(it)) {
                        scaffoldNavigator.currentDestination?.content?.let {
                            HtmlRender(it)
                        }
                    }
                }
            )
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
private fun TopBarGames(scaffoldNavigator: ThreePaneScaffoldNavigator<String>) {
    CenterAlignedTopAppBar(
        title = { Text("Games") },
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
            ListGameItem("New Wordle", "newwordle", scaffoldNavigator)
            HorizontalDivider()
        }
        item {
            ListGameItem("Sudoku Detective", "sudokudetective", scaffoldNavigator)
            HorizontalDivider()
        }
        item {
            ListGameItem("Equation Explorer", "equationexplorer", scaffoldNavigator)
            HorizontalDivider()
        }
        item {
            ListGameItem("Cipher Chronicles", "cipherchronicles", scaffoldNavigator)
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
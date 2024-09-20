package com.example.htmlgameshow.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListDetailPane() {
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<String>()
    val allItems = listOf("New wordle", "New wordle2")
    Scaffold(
        topBar = {
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
                },
            )
        },
        content = {
            ListDetailPaneScaffold(
                directive = scaffoldNavigator.scaffoldDirective,
                value = scaffoldNavigator.scaffoldValue,
                listPane = {
                    AnimatedPane(
                        modifier = Modifier.preferredWidth(200.dp),
                    ) {
                        LazyColumn(
                            modifier = Modifier.padding(it),
                        ) {
                            item {
                                ListItem(
                                    headlineContent = {
                                        Text("New Wordle")
                                    },
                                    modifier = Modifier.clickable {
                                        scaffoldNavigator.navigateTo(
                                            ListDetailPaneScaffoldRole.Detail,
                                            "newwordle"
                                        )
                                    }
                                )
                                HorizontalDivider()
                            }
                            item {
                                ListItem(
                                    headlineContent = {
                                        Text("New Wordle 2", maxLines = 1)
                                    },
                                    modifier = Modifier.clickable {
                                        scaffoldNavigator.navigateTo(
                                            ListDetailPaneScaffoldRole.Detail,
                                            "newwordle"
                                        )
                                    }
                                )
                                HorizontalDivider()
                            }
                            item {
                                ListItem(
                                    headlineContent = {
                                        Text("Sudoku Detective", maxLines = 1)
                                    },
                                    modifier = Modifier.clickable {
                                        scaffoldNavigator.navigateTo(
                                            ListDetailPaneScaffoldRole.Detail,
                                            "sudokudetective"
                                        )
                                    }
                                )
                                HorizontalDivider()
                            }
                            item {
                                ListItem(
                                    headlineContent = {
                                        Text("Equation Explorer", maxLines = 1)
                                    },
                                    modifier = Modifier.clickable {
                                        scaffoldNavigator.navigateTo(
                                            ListDetailPaneScaffoldRole.Detail,
                                            "equationexplorer"
                                        )
                                    }
                                )
                                HorizontalDivider()
                            }
                            item {
                                ListItem(
                                    headlineContent = {
                                        Text("Cipher Chronicles", maxLines = 1)
                                    },
                                    modifier = Modifier.clickable {
                                        scaffoldNavigator.navigateTo(
                                            ListDetailPaneScaffoldRole.Detail,
                                            "cipherchronicles"
                                        )
                                    }
                                )
                            }
                        }
                    }
                },
                detailPane = {
                    AnimatedPane(modifier = Modifier) {
                        scaffoldNavigator.currentDestination?.content?.let {
                            HtmlRender(it)
                        }
                    }
                }
            )
        }
    )
}
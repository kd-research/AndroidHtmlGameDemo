package com.example.htmlgameshow.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.htmlgameshow.R

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListingGameByGridItem(
    target: String,
    scaffoldNavigator: ThreePaneScaffoldNavigator<String>,
    thumbnailImage: Int = R.drawable.default_thumbnail,
    gameTitle: String
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .padding(20.dp)
            .size(width = 180.dp, height = 200.dp),
        onClick = { scaffoldNavigator.navigateTo(ListDetailPaneScaffoldRole.Detail, target) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            // Heading text for the image
            Text(
                text = gameTitle,
                modifier = Modifier
                    .padding(bottom = 8.dp) // Add some space between the heading and image
                    .align(Alignment.CenterHorizontally), // Center the text
                style = MaterialTheme.typography.headlineSmall // Style the heading as per your theme
            )
            // Image below the heading
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .height(140.dp), // Control the size of the image
                painter = painterResource(thumbnailImage),
                contentDescription = target,
                contentScale = ContentScale.Crop
            )
        }
    }
}
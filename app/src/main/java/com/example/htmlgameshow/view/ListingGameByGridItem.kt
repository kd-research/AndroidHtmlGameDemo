package com.example.htmlgameshow.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.htmlgameshow.R

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListingGameByGridItem(
    target: String,
    scaffoldNavigator: ThreePaneScaffoldNavigator<String>,
    thumbnailImage: Int = R.drawable.default_thumbnail
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
        Image(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(5.dp)),
            painter = painterResource(thumbnailImage),
            contentDescription = target,
            contentScale = ContentScale.Crop
        )
    }
}
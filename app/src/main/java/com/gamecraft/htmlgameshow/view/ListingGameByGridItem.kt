package com.gamecraft.htmlgameshow.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gamecraft.htmlgameshow.data.Game

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListingGameByGridItem(
    target: Game,
    onFavouriteClick: () -> Unit,
    onItemClick: () -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .size(width = 180.dp, height = 195.dp),
        onClick = onItemClick
    ) {
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            AsyncImage(
                modifier = Modifier
                    .size(width = 160.dp, height = 160.dp)
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                model = "file:///android_asset/icons/" + target.icon,
                contentDescription = target.toString(),
                contentScale = ContentScale.Crop
            )

        }
        Box(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text("${target.name}\nHigh Score: ${target.highestScore}", fontFamily = FontFamily.SansSerif, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            IconButton(
                modifier = Modifier.absoluteOffset(x = 110.dp, y = -5.dp),
                onClick = onFavouriteClick,
            ) {
                if (target.favourite)
                    Icon(Icons.Filled.Star, contentDescription = "Favourite", tint = Color.Yellow)
                else
                    Icon(Icons.Outlined.Star, contentDescription = "Not Favourite", tint = Color.Gray)
            }
        }
    }
}
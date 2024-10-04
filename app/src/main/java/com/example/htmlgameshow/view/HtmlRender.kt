package com.example.htmlgameshow.view

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.htmlgameshow.R

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HtmlRender(html_name: String, modifier: Modifier = Modifier) {
    if (html_name == "_settings")    {
        SettingPage(modifier = modifier)
    }
    else {

        var showImage by remember { mutableStateOf(true) } // State to control the display of the image

        LaunchedEffect(Unit) {
            // Wait for 3 seconds before switching to WebView
            kotlinx.coroutines.delay(3000)
            showImage = false
        }

        if (showImage) {
            // Display game image with black background
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                val gameImage = displayImages[html_name] ?: R.drawable.default_thumbnail

                Image(
                    painter = painterResource(id = gameImage),
                    contentDescription = null,
                    modifier = Modifier
                        //.fillMaxSize() // Fill the maximum available size
                        //.padding(10.dp) // Optional: add padding if desired
                        .fillMaxWidth() // Set the image width to match the screen width
                        .align(Alignment.Center) // Center the image vertically in the Box
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        } else {
            // After delay, show the WebView with the HTML game

            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center
            ) {
                AndroidView(
                    factory = {
                        WebView(it).apply {
                            settings.javaScriptEnabled = true
                            settings.allowFileAccess = true
                            settings.useWideViewPort = true
                            //settings.loadWithOverviewMode = true

                            layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )

                            //webViewClient = WebViewClient()
                            webChromeClient = WebChromeClient()

                            loadUrl("file:///android_asset/$html_name.html")
                        }
                    })
            }
        }
    }
}

@Composable
fun SettingPage(modifier: Modifier = Modifier) {
    Text("Setting Page", modifier = modifier)
}

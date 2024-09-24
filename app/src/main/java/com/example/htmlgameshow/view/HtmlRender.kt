package com.example.htmlgameshow.view

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HtmlRender(html_name: String, modifier: Modifier = Modifier) {
    if (html_name == "_settings")    {
        SettingPage(modifier = modifier)
    }
    else {
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

@Composable
fun SettingPage(modifier: Modifier = Modifier) {
    Text("Setting Page", modifier = modifier)
}

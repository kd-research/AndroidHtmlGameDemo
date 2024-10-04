package com.gamecraft.htmlgameshow.view

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.gamecraft.htmlgameshow.data.Game
import com.gamecraft.htmlgameshow.jshelper.GameJsInterface
import com.gamecraft.htmlgameshow.viewmodel.GameIndexViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HtmlRender(
    game: Game,
    navigator: ThreePaneScaffoldNavigator<Game>,
    viewModel: GameIndexViewModel,
    modifier: Modifier = Modifier
) {
    var webView by remember { mutableStateOf<WebView?>(null) }

    Column(
        modifier = modifier, verticalArrangement = Arrangement.Center
    ) {
        AndroidView(factory = {
            webView = WebView(it)
            webView!!.apply {
                settings.javaScriptEnabled = true
                settings.allowFileAccess = true
                settings.useWideViewPort = true
                settings.domStorageEnabled = true

                val jsInterface = GameJsInterface(game, viewModel)

                addJavascriptInterface(jsInterface, "Platform")
                //settings.loadWithOverviewMode = true

                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )

                //webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()

                loadUrl("file:///android_asset/htmls/" + game.html)
            }
        })
    }

    Box {
        TopBarGames(
            scaffoldNavigator = navigator,
            closeWebView = { webView?.destroy() },
            modifier = Modifier
                .size(30.dp)
                .absoluteOffset(x = 10.dp, y = 30.dp)
        )
    }
}


@Composable
fun SettingPage(modifier: Modifier = Modifier) {
    Text("Setting Page", modifier = modifier)
}

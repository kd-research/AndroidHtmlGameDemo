package com.example.htmlgameshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import com.example.htmlgameshow.view.HtmlRender
import com.example.htmlgameshow.view.ListDetailPane

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListDetailPane()
        }
    }
}
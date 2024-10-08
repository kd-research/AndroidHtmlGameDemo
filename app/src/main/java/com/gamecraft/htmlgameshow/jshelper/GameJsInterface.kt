package com.gamecraft.htmlgameshow.jshelper

import android.webkit.JavascriptInterface
import com.gamecraft.htmlgameshow.data.Game
import com.gamecraft.htmlgameshow.data.GameDatabase
import com.gamecraft.htmlgameshow.viewmodel.GameIndexViewModel

class GameJsInterface(val game: Game, private var viewModel: GameIndexViewModel) {
    val VERSION = "1.0"
    @JavascriptInterface
    fun majorVersion(): Int { return VERSION.split(".")[0].toInt() }

    @JavascriptInterface
    fun minorVersion(): Int { return VERSION.split(".")[1].toInt() }

    @JavascriptInterface
    fun getHighestScore(): Int { return game.highestScore }

    @JavascriptInterface
    fun setHighestScore(newScore: Int) {
        println("setHighestScore: $newScore")

        Thread {
            if (newScore <= game.highestScore)
                return@Thread
            GameDatabase.db().update(game.copy(highestScore = newScore))
            viewModel.fetchGames()
        }.start()
    }

    @JavascriptInterface
    fun getSoundVolume(): Int { return 100 }

    @JavascriptInterface
    fun getPreferredDifficulty(): Int { return 3 }
}
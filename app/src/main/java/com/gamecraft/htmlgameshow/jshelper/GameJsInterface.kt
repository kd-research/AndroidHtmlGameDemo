package com.gamecraft.htmlgameshow.jshelper

import android.webkit.JavascriptInterface
import com.gamecraft.htmlgameshow.data.Game
import com.gamecraft.htmlgameshow.data.GameDatabase
import com.gamecraft.htmlgameshow.viewmodel.GameIndexViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameJsInterface(val game: Game, private var viewModel: GameIndexViewModel) {

    @JavascriptInterface
    fun getHighestScore(): Int { return game.highestScore }

    @JavascriptInterface
    fun setHighestScore(newScore: Int) {
        println("setHighestScore: $newScore")

        Thread {
            GameDatabase.db().update(game.copy(highestScore = newScore))
            viewModel.fetchGames()
        }.start()
    }

}
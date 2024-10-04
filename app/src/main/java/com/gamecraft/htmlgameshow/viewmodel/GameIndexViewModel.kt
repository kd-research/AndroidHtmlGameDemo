package com.gamecraft.htmlgameshow.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamecraft.htmlgameshow.data.Game
import com.gamecraft.htmlgameshow.data.GameDatabase
import kotlinx.coroutines.launch

class GameIndexViewModel : ViewModel() {
    var games by mutableStateOf<List<Game>>(emptyList())
        private set

    init {
        fetchGames()
    }

    fun fetchGames() {
        viewModelScope.launch {
            games = GameDatabase.db().all().sortedBy { !it.favourite }
        }
    }

    fun toggleFavourite(game: Game) {
        viewModelScope.launch {
            GameDatabase.db().update(game.copy(favourite = !game.favourite))
            fetchGames()
        }
    }

    fun resetDb(context: Context) {
        viewModelScope.launch {
            GameDatabase.resetDb(context)
            fetchGames()
        }
    }

    fun resetHighScores() {
        viewModelScope.launch {
            GameDatabase.resetHighScores()
            fetchGames()
        }
    }
}
package com.gamecraft.htmlgameshow.data

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class GameImport(
    val id: Int,
    val name: String,
    val icon: String,
    val html: String
) {
    companion object {
        fun games(context: Context): List<GameImport> {
            context.assets.open("games.json").bufferedReader().use {
                val json = Json { ignoreUnknownKeys = true }
                return json.decodeFromString(it.readText())
            }
        }
    }
}
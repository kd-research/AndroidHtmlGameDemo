package com.gamecraft.htmlgameshow.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract fun dao(): GameDao

    companion object {
        @Volatile
        var DATABASE: GameDatabase? = null

        fun db(): GameDao {
            return DATABASE!!.dao()
        }

        fun initDb(context: Context) {
            DATABASE = Room.databaseBuilder(
                context.applicationContext,
                GameDatabase::class.java,
                "games.db"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

            importGames(context)
        }

        private fun importGames(context: Context) {
            val existingIds = db().ids()

            for (gameImport in GameImport.games(context)) {
                val gameId = gameImport.id

                if (!existingIds.contains(gameId)) {
                    val game = Game(
                    id = gameImport.id,
                    name = gameImport.name,
                    icon = gameImport.icon,
                    html = gameImport.html,
                    highestScore = 0,
                    favourite = false
                )
                    db().insert(game)
                } else {
                    val oldGame = db().find(gameId)
                    val newGame = Game(
                        id = gameImport.id,
                        name = gameImport.name,
                        icon = gameImport.icon,
                        html = gameImport.html,
                        highestScore = oldGame.highestScore,
                        favourite = oldGame.favourite
                    )
                    db().update(newGame)
                }
            }
        }

        fun resetDb(context: Context) {
            DATABASE!!.clearAllTables()
            importGames(context)
        }

        fun resetHighScores() {
            db().resetHighestScore()
        }
    }
}
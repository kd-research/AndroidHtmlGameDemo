package com.gamecraft.htmlgameshow.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameDao {
    @Query("Select * From game")
    fun all(): List<Game>

    @Query("Select * From game Where id = :id")
    fun find(id: Int): Game

    @Query("Select id From game")
    fun ids(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(game: Game)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(game: Game)

    @Query("Update game Set highestScore = 0")
    fun resetHighestScore()
}
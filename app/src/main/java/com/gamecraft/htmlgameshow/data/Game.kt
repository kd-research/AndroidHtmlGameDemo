package com.gamecraft.htmlgameshow.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Game(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val icon: String,
    @ColumnInfo val html: String,
    @ColumnInfo val highestScore: Int,
    @ColumnInfo val favourite: Boolean = false,
)


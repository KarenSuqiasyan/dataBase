package com.example.actorsdatabaseapp.room.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieRoom(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "actorRoomId")
    val id: Int = 0,
    val year: Int,
    val imdbRate: Double,
    val movieName: String,
    val actorId : Int
)
package com.example.actorsdatabaseapp.room.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actor_table")
data class ActorRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val surName: String,
    val age: Int
)

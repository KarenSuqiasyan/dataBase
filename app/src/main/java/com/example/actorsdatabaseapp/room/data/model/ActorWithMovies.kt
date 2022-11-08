package com.example.actorsdatabaseapp.room.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.actorsdatabaseapp.room.ui.fragments.RoomMovieListFragment

data class ActorWithMovies(
    @Embedded val actorRoom: ActorRoom,
    @Relation(
        parentColumn = "id",
        entityColumn = "actorId",
        entity = MovieRoom::class
    )
    val movieList: List<MovieRoom>)

//    val movieId: Int,
//    val actorName: String,
//    val year: Int,
//    val imdbRate: Double,
//    val movieName: String,
//    val actorId: Int


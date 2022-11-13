package com.example.actorsdatabaseapp.room.data.repository

import androidx.lifecycle.LiveData
import com.example.actorsdatabaseapp.room.data.ActorDao
import com.example.actorsdatabaseapp.room.data.model.ActorRoom
import com.example.actorsdatabaseapp.room.data.model.ActorWithMovies
import com.example.actorsdatabaseapp.room.data.model.MovieRoom

class MovieRepository(private val actorDao: ActorDao) {

    val getAllActorsWithMovies: LiveData<List<ActorWithMovies>> = actorDao.getActorWithMovies()


     suspend fun deleteMovie(movieRoom: MovieRoom) {
        actorDao.deleteMovie(movieRoom)
    }
}
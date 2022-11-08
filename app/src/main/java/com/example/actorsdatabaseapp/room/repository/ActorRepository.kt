package com.example.actorsdatabaseapp.room.repository

import androidx.lifecycle.LiveData
import com.example.actorsdatabaseapp.room.data.ActorDao
import com.example.actorsdatabaseapp.room.data.model.ActorRoom
import com.example.actorsdatabaseapp.room.data.model.ActorWithMovies
import com.example.actorsdatabaseapp.room.data.model.MovieRoom

class ActorRepository(private val actorDao: ActorDao) {

    val getAllActors: LiveData<List<ActorRoom>> = actorDao.getAllActors()

     fun addMovie(movieRoom: MovieRoom) {
        actorDao.addMovie(movieRoom)
    }

    fun addActor(actorRoom: ActorRoom) {
        actorDao.addActor(actorRoom)
    }

    fun deleteActor(actorRoom: ActorRoom) {
        actorDao.deleteActor(actorRoom)
    }

}
package com.example.actorsdatabaseapp.room.data.repository

import androidx.lifecycle.LiveData
import com.example.actorsdatabaseapp.room.data.ActorDao
import com.example.actorsdatabaseapp.room.data.model.ActorRoom
import com.example.actorsdatabaseapp.room.data.model.MovieRoom

class ActorRepository(private val actorDao: ActorDao) {

    val getAllActors: LiveData<List<ActorRoom>> = actorDao.getAllActors()

    suspend fun addMovie(movieRoom: MovieRoom) {
        actorDao.addMovie(movieRoom)
    }

    suspend fun addActor(actorRoom: ActorRoom) {
        actorDao.addActor(actorRoom)
    }

    suspend fun deleteActor(actorRoom: ActorRoom) {
        actorDao.deleteActor(actorRoom)
    }

}
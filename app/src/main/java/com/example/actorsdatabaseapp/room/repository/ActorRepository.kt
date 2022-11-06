package com.example.actorsdatabaseapp.room.repository

import androidx.lifecycle.LiveData
import com.example.actorsdatabaseapp.room.data.ActorDao
import com.example.actorsdatabaseapp.room.data.model.ActorRoom

class ActorRepository(private val actorDao: ActorDao) {

    val getAllActors: LiveData<List<ActorRoom>> = actorDao.getAllActors()

    suspend fun addActor(actorRoom: ActorRoom) {
        actorDao.addActor(actorRoom)
    }

    suspend fun deleteActor(actorRoom: ActorRoom) {
        actorDao.deleteActor(actorRoom)
    }

}
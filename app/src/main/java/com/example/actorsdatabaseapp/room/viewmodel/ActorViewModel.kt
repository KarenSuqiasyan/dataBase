package com.example.actorsdatabaseapp.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.example.actorsdatabaseapp.room.data.ActorDataBase
import com.example.actorsdatabaseapp.room.data.model.ActorRoom
import com.example.actorsdatabaseapp.room.data.model.ActorWithMovies
import com.example.actorsdatabaseapp.room.data.model.MovieRoom
import com.example.actorsdatabaseapp.room.data.repository.ActorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActorViewModel(application: Application) : AndroidViewModel(application) {

    val getAllActors: LiveData<List<ActorRoom>>
    private val repository: ActorRepository

    init {
        val actorDao = ActorDataBase.getInstance(application).actorDao
        repository = ActorRepository(actorDao)
        getAllActors = repository.getAllActors
    }

    fun addMovie(movieRoom: MovieRoom) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMovie(movieRoom)
        }
    }

    fun addActor(actorRoom: ActorRoom) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addActor(actorRoom)
        }
    }

    fun deleteActor(actorRoom: ActorRoom) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteActor(actorRoom)
        }
    }

}
package com.example.actorsdatabaseapp.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.actorsdatabaseapp.room.data.ActorDataBase
import com.example.actorsdatabaseapp.room.data.model.ActorWithMovies
import com.example.actorsdatabaseapp.room.data.model.MovieRoom
import com.example.actorsdatabaseapp.room.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepository
    val getAllActorWithMovies: LiveData<List<ActorWithMovies>>


    init {
        val actorDao = ActorDataBase.getInstance(application).actorDao
        repository = MovieRepository(actorDao)
        getAllActorWithMovies = repository.getAllActorsWithMovies
    }

    fun deleteMovie(movieRoom: MovieRoom) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMovie(movieRoom)
        }
    }
}



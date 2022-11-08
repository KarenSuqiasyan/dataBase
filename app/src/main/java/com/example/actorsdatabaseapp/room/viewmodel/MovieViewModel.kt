package com.example.actorsdatabaseapp.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.actorsdatabaseapp.room.data.ActorDataBase
import com.example.actorsdatabaseapp.room.data.model.ActorWithMovies
import com.example.actorsdatabaseapp.room.data.model.MovieRoom
import com.example.actorsdatabaseapp.room.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepository


    init {
        val actorDao = ActorDataBase.getInstance(application).actorDao
        repository = MovieRepository(actorDao)
    }

    fun addMovie(movieRoom: MovieRoom) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMovie(movieRoom)
        }
    }

//    fun deleteMovie(actorWithMovies: ActorWithMovies) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteMovie(actorWithMovies)
//        }
//    }
}
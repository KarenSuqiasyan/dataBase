package com.example.actorsdatabaseapp.room.viewmodel.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MovieViewModel(application: Application) : AndroidViewModel(application) {

//    private val repository: MovieRepository
//    val getAllActorWithMovies: LiveData<List<ActorWithMovies>>
//
//
//    init {
//        val actorDao = ActorDataBase.getInstance(application).actorDao
//        repository = MovieRepository(actorDao)
//        getAllActorWithMovies = repository.getAllActorsWithMovies
//    }
//
////    fun addMovie(movieRoom: MovieRoom) {
////        viewModelScope.launch(Dispatchers.IO) {
////            repository.addMovie(movieRoom)
////        }
////    }
//
//    fun deleteMovie(actorWithMovies: ActorWithMovies) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteMovie(actorWithMovies)
//        }
//    }
}
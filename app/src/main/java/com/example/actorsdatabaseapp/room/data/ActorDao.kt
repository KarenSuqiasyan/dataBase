package com.example.actorsdatabaseapp.room.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.actorsdatabaseapp.room.data.model.ActorRoom
import com.example.actorsdatabaseapp.room.data.model.ActorWithMovies
import com.example.actorsdatabaseapp.room.data.model.MovieRoom

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addActor(actorRoom: ActorRoom)

    @Delete
    fun deleteActor(actorRoom: ActorRoom)

//    @Delete
//    fun deleteMovie(actorWithMovies: ActorWithMovies)

    @Query("SELECT * FROM actor_table ORDER BY id ASC")
    fun getAllActors(): LiveData<List<ActorRoom>>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun addMovie(movieRoom: MovieRoom)

//    @Transaction
//    @Query("SELECT * FROM actor_table inner join movie_table on actor_table.id = actorId")
//    fun getActorsWithMovies(): LiveData<List<ActorWithMovies>>

//    @Query("SELECT * FROM actor_table INNER JOIN movie_table ON actor_table.id = movie_table.actorId ")
//    fun getActorsWithMovies(): LiveData<List<ActorWithMovies>>


//    @Transaction
//    @Query("SELECT * FROM actor_table")
//    fun getActorsWithMovies(): LiveData<List<ActorWithMovies>>

}
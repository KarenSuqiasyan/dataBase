package com.example.actorsdatabaseapp.room.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.actorsdatabaseapp.room.data.model.ActorRoom
import com.example.actorsdatabaseapp.room.data.model.ActorWithMovies
import com.example.actorsdatabaseapp.room.data.model.MovieRoom

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addActor(actorRoom: ActorRoom)

    @Delete
    suspend fun deleteActor(actorRoom: ActorRoom)

    @Delete
    suspend fun deleteMovie(movieRoom: MovieRoom)

    @Query("SELECT * FROM actor_table ORDER BY id ASC")
    fun getAllActors(): LiveData<List<ActorRoom>>

    @Transaction
    @Query("SELECT * FROM actor_table")
    fun getActorWithMovies(): LiveData<List<ActorWithMovies>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(vararg movieRoom: MovieRoom)

//    @Transaction
//    @Query("SELECT * FROM actor_table inner join movie_table on actor_table.id = actorId")
//    fun getActorsWithMovies(): LiveData<List<ActorWithMovies>>

//    @Query("SELECT * FROM actor_table INNER JOIN movie_table ON actor_table.id = movie_table.actorId ")
//    fun getActorsWithMovies(): LiveData<List<ActorWithMovies>>


//    @Transaction
//    @Query("SELECT * FROM actor_table")
//    fun getActorsWithMovies(): LiveData<List<ActorWithMovies>>

}
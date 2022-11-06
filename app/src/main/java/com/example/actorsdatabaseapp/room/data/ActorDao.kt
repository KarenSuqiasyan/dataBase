package com.example.actorsdatabaseapp.room.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.actorsdatabaseapp.room.data.model.ActorRoom

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addActor(actorRoom: ActorRoom)

    @Delete
    suspend fun deleteActor(actorRoom: ActorRoom)

    @Query("SELECT * FROM actor_table ORDER BY id ASC")
    fun getAllActors(): LiveData<List<ActorRoom>>

}
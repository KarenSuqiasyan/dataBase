package com.example.actorsdatabaseapp.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.actorsdatabaseapp.room.data.model.ActorRoom
import com.example.actorsdatabaseapp.room.data.model.MovieRoom
import com.example.actorsdatabaseapp.room.data.model.PetsTypeConverter

@Database(entities = [ActorRoom::class, MovieRoom::class], version = 1, exportSchema = false)
@TypeConverters(PetsTypeConverter::class)
abstract class ActorDataBase : RoomDatabase() {

    abstract val actorDao: ActorDao

    companion object {
        @Volatile
        private var INSTANCE: ActorDataBase? = null

        fun getInstance(context: Context): ActorDataBase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ActorDataBase::class.java,
                    "actor_database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
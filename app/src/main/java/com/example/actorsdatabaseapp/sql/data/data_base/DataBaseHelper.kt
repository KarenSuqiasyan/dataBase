package com.example.actorsdatabaseapp.sql.data.data_base

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.JsonReader
import android.widget.Toast
import com.example.actorsdatabaseapp.sql.Convert
import com.example.actorsdatabaseapp.sql.data.entity.ActorEntity
import com.example.actorsdatabaseapp.sql.data.entity.ActorEntity.ACTORS_AGE
import com.example.actorsdatabaseapp.sql.data.entity.ActorEntity.ACTORS_ID
import com.example.actorsdatabaseapp.sql.data.entity.ActorEntity.ACTORS_NAME
import com.example.actorsdatabaseapp.sql.data.entity.ActorEntity.ACTORS_SURNAME
import com.example.actorsdatabaseapp.sql.data.entity.ActorEntity.ACTORS_TABLE_NAME
import com.example.actorsdatabaseapp.sql.data.entity.ActorEntity.PETS
import com.example.actorsdatabaseapp.sql.data.entity.ActorEntity.SQL_DELETE_TABLE_ACTORS
import com.example.actorsdatabaseapp.sql.data.entity.MoviesEntity
import com.example.actorsdatabaseapp.sql.data.model.Actor
import com.example.actorsdatabaseapp.sql.data.model.ActorMovies
import com.example.actorsdatabaseapp.sql.data.model.Movie
import com.example.actorsdatabaseapp.sql.data.model.Pets

class DataBaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "Actors_db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(ActorEntity.SQL_CREATE_TABLE_ACTORS)
        db?.execSQL(MoviesEntity.SQL_CREATE_MOVIE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_TABLE_ACTORS)
        db?.execSQL(MoviesEntity.SQL_DELETE_TABLE_MOVIES)
        onCreate(db)
    }

    fun addActor(actor: Actor): Long {
        var result = -1L
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.apply {
            put(ACTORS_NAME, actor.name)
            put(ACTORS_SURNAME, actor.surName)
            put(ACTORS_AGE, actor.age)
            put(PETS, Convert.listToJson(actor.pets))
        }
        try {
            result = db.insertOrThrow(ACTORS_TABLE_NAME, null, contentValues)
        } catch (e: SQLiteException) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        } finally {
            db.close()
        }
        return result
    }

    fun deleteActor(id: Int): Int {
        var result = -1
        val db = this.writableDatabase

        val whereClause = "$ACTORS_ID = ?"
        val whereArgs = arrayOf("$id")
        result = db.delete(ACTORS_TABLE_NAME, whereClause, whereArgs)
        db.close()
        return result
    }

    fun getActors(): ArrayList<Actor> {

        val db = this.readableDatabase
        val actorsList: ArrayList<Actor> = ArrayList()
        val query = "SELECT * FROM $ACTORS_TABLE_NAME ORDER BY $ACTORS_ID DESC"

        val cursor = db.rawQuery(query, null)
        cursor?.let {
            if (cursor.columnCount > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        val id = cursor.getInt(cursor.getColumnIndexOrThrow(ACTORS_ID))
                        val name = cursor.getString(cursor.getColumnIndexOrThrow(ACTORS_NAME))
                        val surName = cursor.getString(cursor.getColumnIndexOrThrow(ACTORS_SURNAME))
                        val age = cursor.getString(cursor.getColumnIndexOrThrow(ACTORS_AGE))
                        val pet = cursor.getString(cursor.getColumnIndexOrThrow(PETS))
                        actorsList.add(Actor(id, name, surName, age, Convert.jsonToList(pet) as ArrayList<Pets>))
                    } while (cursor.moveToNext())
                }
            }
            cursor.close()
            db.close()
        }
        return actorsList
    }

    fun addMovie(movie: Movie) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(MoviesEntity.ACTOR_ID, movie.actorId)
            put(MoviesEntity.MOVIE_RATE, movie.imdbRate)
            put(MoviesEntity.MOVIE_NAME, movie.movieName)
            put(MoviesEntity.MOVIE_YEAR, movie.year)

        }
        try {
            db.insert(MoviesEntity.MOVIE_TABLE_NAME, null, contentValues)
            db.close()

        } catch (e: SQLiteException) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteMovie(id: Int): Int {
        var result = -1
        val db = this.writableDatabase

        val whereClause = "${MoviesEntity.MOVIE_ID} = ?"
        val whereArgs = arrayOf("$id")
        result = db.delete(MoviesEntity.MOVIE_TABLE_NAME, whereClause, whereArgs)
        db.close()
        return result
    }

    fun getMovieList(): ArrayList<ActorMovies> {

        val movieLIst = ArrayList<ActorMovies>()
        val db = readableDatabase

        val query = "SELECT * FROM ${MoviesEntity.MOVIE_TABLE_NAME} AS T1 " +
                "JOIN $ACTORS_TABLE_NAME AS T2 " +
                "ON T1.${MoviesEntity.ACTOR_ID} = T2.$ACTORS_ID " +
                "ORDER BY T1.${MoviesEntity.ACTOR_ID} DESC"

        val cursor = db.rawQuery(query, null)
        cursor?.let {
            if (cursor.columnCount > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        val actorId = cursor.getInt(cursor.getColumnIndexOrThrow(MoviesEntity.ACTOR_ID))
                        val name = cursor.getString(cursor.getColumnIndexOrThrow(ACTORS_NAME))
                        val movieRate = cursor.getDouble(cursor.getColumnIndexOrThrow(MoviesEntity.MOVIE_RATE))
                        val movieYear = cursor.getInt(cursor.getColumnIndexOrThrow(MoviesEntity.MOVIE_YEAR))
                        val movieName = cursor.getString(cursor.getColumnIndexOrThrow(MoviesEntity.MOVIE_NAME))
                        val movieId = cursor.getInt(cursor.getColumnIndexOrThrow(MoviesEntity.MOVIE_ID))

                        movieLIst.add(ActorMovies(actorId, name, movieRate, movieYear, movieName, movieId))

                    } while (cursor.moveToNext())
                }
            }
            cursor.close()
            db.close()
        }
        return movieLIst
    }
}



package com.example.actorsdatabaseapp.sql.data.entity

object MoviesEntity {

    const val MOVIE_ID = "id"
    const val ACTOR_ID = "actor_id"
    const val MOVIE_YEAR = "movie_year"
    const val MOVIE_RATE = "movie_rate"
    const val MOVIE_NAME = "movie_name"



    const val MOVIE_TABLE_NAME = "movie_table"

    const val SQL_CREATE_MOVIE_TABLE = "CREATE TABLE  $MOVIE_TABLE_NAME ( " +
            "$MOVIE_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$ACTOR_ID INTEGER NOT NULL, " +
            "$MOVIE_RATE INTEGER NOT NULL, " +
            "$MOVIE_YEAR INTEGER NOT NULL, " +
            "$MOVIE_NAME TEXT NOT NULL, " +
            "FOREIGN KEY ($ACTOR_ID) " +
            "REFERENCES ${ActorEntity.ACTORS_TABLE_NAME}" +
            "(${ActorEntity.ACTORS_ID}))"

    const val SQL_DELETE_TABLE_MOVIES = "DROP TABLE IF EXISTS $MOVIE_TABLE_NAME"
}
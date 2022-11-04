package com.example.actorsdatabaseapp.data.entity

object ActorEntity {

    const val ACTORS_TABLE_NAME = "Actors_table"
    const val ACTORS_ID = "_id"
    const val ACTORS_NAME = "name"
    const val ACTORS_SURNAME = "surname"
    const val ACTORS_AGE = "age"

    const val SQL_CREATE_TABLE_ACTORS = "CREATE TABLE  $ACTORS_TABLE_NAME ( " +
            "$ACTORS_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$ACTORS_NAME TEXT NOT NULL, " +
            "$ACTORS_SURNAME TEXT NOT NULL, " +
            "$ACTORS_AGE TEXT NOT NULL )"

    const val SQL_DELETE_TABLE_ACTORS = "DROP TABLE IF EXISTS $ACTORS_TABLE_NAME"

}
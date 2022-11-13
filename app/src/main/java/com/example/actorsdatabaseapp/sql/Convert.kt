package com.example.actorsdatabaseapp.sql

import com.example.actorsdatabaseapp.sql.data.model.Pets
import com.google.gson.Gson

class Convert {

    companion object {
        fun listToJson(value: ArrayList<Pets>): String = Gson().toJson(value)

        fun jsonToList(value: String) = Gson().fromJson(value, Array<Pets>::class.java).toMutableList()
    }
}
package com.example.actorsdatabaseapp.room.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "actor_table")
data class ActorRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val surName: String,
    val age: Int,
    val pets: List<Pet>?
)

class PetsTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Pet>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Pet>::class.java).toList()
}


//class PetTypeConverter {
//    @TypeConverter
//    fun fromPet(pet: Pet): ArrayList<Pet> {
//        val listType = object : TypeToken<ArrayList<Pet>>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromArrayList(list: ArrayList<Pet>): String {
//        return Gson().toJson(list)
//    }
//}


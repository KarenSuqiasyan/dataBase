package com.example.actorsdatabaseapp

import android.app.Application
import com.example.actorsdatabaseapp.sql.data.data_base.DataBaseHelper

class MyApplication : Application() {

    lateinit var dbHelper : DataBaseHelper

    override fun onCreate() {
        super.onCreate()
        dbHelper = DataBaseHelper(applicationContext)
    }
}
package com.example.moviedb

import android.app.Application
import androidx.room.Room
import com.example.moviedb.data.AppDatabase
import com.example.moviedb.data.DefaultAppContainer
import com.example.moviedb.data.IAppContainer

class MovieDBApplication: Application()  {
    lateinit var container: IAppContainer
    lateinit var db: AppDatabase
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "MovieDBAppDatabase"
        )
            .build()
        container = DefaultAppContainer(db)
    }
}
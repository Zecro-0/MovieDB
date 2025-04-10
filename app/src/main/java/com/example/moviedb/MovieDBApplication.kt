package com.example.moviedb

import android.app.Application
import com.example.moviedb.data.DefaultAppContainer
import com.example.moviedb.data.IAppContainer

class MovieDBApplication: Application()  {
    lateinit var container: IAppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
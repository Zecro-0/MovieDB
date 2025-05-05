package com.example.moviedb.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GenreDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Genre)

    @Query("SELECT * FROM genre")
    suspend fun getAll(): List<Genre>

    @Query("SELECT * FROM genre WHERE id = :id")
     suspend fun getById(id: Int): Genre

}
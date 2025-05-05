package com.example.moviedb.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Movie)

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getById(id: Int): Movie

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>
}
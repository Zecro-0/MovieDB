package com.example.moviedb.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Review)

    @Query("SELECT * FROM review WHERE id = :id")
    suspend fun getById(id: Int): Review

    @Query("SELECT * FROM review")
    suspend fun getAll(): List<Review>
}
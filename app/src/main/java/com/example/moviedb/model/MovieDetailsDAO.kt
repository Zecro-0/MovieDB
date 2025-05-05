package com.example.moviedb.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDetailsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: MovieDetailResponse)

    @Query("SELECT * FROM MovieDetailResponse WHERE id = :id")
    suspend fun getById(id: Int): MovieDetailResponse

    @Query("SELECT * FROM MovieDetailResponse")
    suspend fun getAll(): List<MovieDetailResponse>



}
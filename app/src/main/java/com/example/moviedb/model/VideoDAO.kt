package com.example.moviedb.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VideoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: VideoResponse)

    @Query("SELECT * FROM videoresponse WHERE id = :id")
    suspend fun getById(id: Int): VideoResponse

}
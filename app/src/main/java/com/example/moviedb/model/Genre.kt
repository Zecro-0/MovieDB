package com.example.moviedb.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse(
    val genres: List<Genre>
)

@Entity
@Serializable
data class Genre(
    @PrimaryKey
    val id: Int,
    val name: String
)
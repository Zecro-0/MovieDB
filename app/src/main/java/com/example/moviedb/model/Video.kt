package com.example.moviedb.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class VideoResponse(
    @PrimaryKey
    val id: Int,
    @SerialName("results")
    val results: List<Video>
)

@Serializable
data class Video(
    @SerialName("iso_639_1") val iso6391: String,
    @SerialName("iso_3166_1") val iso3166_1: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    @SerialName("published_at") val publishedAt: String,
    val id: String
)


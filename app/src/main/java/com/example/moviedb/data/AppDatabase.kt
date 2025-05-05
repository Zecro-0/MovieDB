package com.example.moviedb.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviedb.model.Genre
import com.example.moviedb.model.GenreDAO
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieDAO
import com.example.moviedb.model.MovieDetailResponse
import com.example.moviedb.model.MovieDetailsDAO
import com.example.moviedb.model.Review
import com.example.moviedb.model.ReviewDAO
import com.example.moviedb.model.VideoDAO
import com.example.moviedb.model.VideoResponse

@Database(entities = [Genre::class, Movie::class, Review::class, MovieDetailResponse::class, VideoResponse::class],
    version = 1,
    exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDAO(): GenreDAO
    abstract fun movieDAO(): MovieDAO
    abstract fun reviewDAO(): ReviewDAO
    abstract fun videoDAO(): VideoDAO
    abstract fun movieDetailsDAO(): MovieDetailsDAO
}
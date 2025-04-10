package com.example.moviedb.data

import com.example.moviedb.model.Movie

interface IMovieRepository {
    suspend fun getMovies(): List<Movie>
}
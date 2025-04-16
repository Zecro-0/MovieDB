package com.example.moviedb.data

import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieDetails

interface IMovieRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovie(id: Int): MovieDetails
}
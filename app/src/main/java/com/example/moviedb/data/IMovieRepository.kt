package com.example.moviedb.data

import com.example.moviedb.model.Genre
import com.example.moviedb.model.GenresResponse
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieApiResponse
import com.example.moviedb.model.MovieDetailResponse
import com.example.moviedb.model.ReviewResponse
import com.example.moviedb.model.VideoResponse

interface IMovieRepository {
    suspend fun getMovies(): MovieApiResponse
    suspend fun getMovie(id: Int): MovieDetailResponse
    suspend fun getMovieAndTvGenres(): GenresResponse
    suspend fun getReviews(id: Int): ReviewResponse
    suspend fun getVideos(id: Int): VideoResponse
}
package com.example.moviedb.network

import com.example.moviedb.model.Genre
import com.example.moviedb.model.GenresResponse
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieApiResponse
import com.example.moviedb.model.MovieDetailResponse
import com.example.moviedb.model.ReviewResponse
import com.example.moviedb.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ITMDBApiService {
    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetailResponse

    @GET("discover/movie")
    suspend fun getMovies(): MovieApiResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): GenresResponse

    @GET("genre/tv/list")
    suspend fun getTvGenres(): GenresResponse

    @GET("movie/{id}/reviews")
    suspend fun getReviews(@Path("id") id: Int): ReviewResponse

    @GET("movie/{id}/videos")
    suspend fun getVideos(@Path("id") id: Int): VideoResponse


}
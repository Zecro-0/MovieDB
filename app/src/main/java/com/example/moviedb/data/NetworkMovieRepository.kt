package com.example.moviedb.data

import com.example.moviedb.model.Genre
import com.example.moviedb.model.GenresResponse
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieApiResponse
import com.example.moviedb.model.MovieDetailResponse
import com.example.moviedb.model.ReviewResponse
import com.example.moviedb.model.VideoResponse
import com.example.moviedb.network.ITMDBApiService

class NetworkMovieRepository (
    private val apiService: ITMDBApiService
): IMovieRepository{
    override suspend fun getMovies(): MovieApiResponse = apiService.getMovies()
    override suspend fun getMovie(id: Int): MovieDetailResponse = apiService.getMovieDetails(id)
    override suspend fun getMovieGenres(): GenresResponse = apiService.getMovieGenres()
    override suspend fun getTvGenres(): GenresResponse = apiService.getTvGenres()
    override suspend fun getReviews(id: Int): ReviewResponse = apiService.getReviews(id)
    override suspend fun getVideos(id: Int): VideoResponse = apiService.getVideos(id)

}
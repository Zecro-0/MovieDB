package com.example.moviedb.data

import com.example.moviedb.model.Genre
import com.example.moviedb.model.GenresResponse
import com.example.moviedb.model.MovieApiResponse
import com.example.moviedb.model.MovieDetailResponse
import com.example.moviedb.model.ReviewResponse
import com.example.moviedb.model.VideoResponse
import com.example.moviedb.network.ITMDBApiService
import com.example.moviedb.network.NetworkCallback
import com.example.moviedb.model.GenreDAO
import com.example.moviedb.model.MovieDAO
import com.example.moviedb.model.MovieDetailsDAO
import com.example.moviedb.model.ReviewDAO
import com.example.moviedb.model.VideoDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkMovieRepository (
    private val apiService: ITMDBApiService,
    private val db: AppDatabase
): IMovieRepository{
    override suspend fun getMovies(): MovieApiResponse{
        val MovieDAO = db.movieDAO()

        if(NetworkCallback.isOnline){
            val genres = apiService.getMovies()
            MovieDAO.insertAll(*genres.results.toTypedArray()) //* spread operator, takes an array and expands each elements from it into the number of items to the same amount of arguments.
            return genres
        }
        val movies = MovieDAO.getAll()
        return MovieApiResponse(results = movies, totalResults = movies.size, page = 1, totalPages = 1)
    }

    override suspend fun getMovie(id: Int): MovieDetailResponse{
        val MovieDetailsDAO = db.movieDetailsDAO()
        if(NetworkCallback.isOnline){
            val movie = apiService.getMovieDetails(id)
            MovieDetailsDAO.insertAll(movie)
            return movie
        }
        val movie = MovieDetailsDAO.getById(id)
        return movie
    }

    override suspend fun getMovieAndTvGenres(): GenresResponse{
        val GenreDAO = db.genreDAO()
        if(NetworkCallback.isOnline){
            val movieGenres = apiService.getMovieGenres()
            val tvGenres = apiService.getTvGenres()
            GenreDAO.insertAll(*movieGenres.genres.toTypedArray()) //* spread operator, takes an array and expands each elements from it into the number of items to the same amount of arguments.
            GenreDAO.insertAll(*tvGenres.genres.toTypedArray())
            val genres = movieGenres.genres + tvGenres.genres
            return GenresResponse(genres = genres)
        }
        return GenresResponse(genres = GenreDAO.getAll())
    }

    override suspend fun getReviews(id: Int): ReviewResponse{
        val ReviewDAO = db.reviewDAO()
        if(NetworkCallback.isOnline){
            val reviews = apiService.getReviews(id)
            ReviewDAO.insertAll(*reviews.results.toTypedArray()) //* spread operator, takes an array and expands each elements from it into the number of items to the same amount of arguments.
            return reviews
        }
        val reviews = ReviewDAO.getAll()
        return ReviewResponse(
            results = reviews, totalResults = reviews.size, page = 1, totalPages = 1,
            id = id
        )
    }
    override suspend fun getVideos(id: Int): VideoResponse{
        val VideoDAO = db.videoDAO()
        if(NetworkCallback.isOnline){
            val videos = apiService.getVideos(id)
            VideoDAO.insertAll(videos)//* spread operator, takes an array and expands each elements from it into the number of items to the same amount of arguments.
            return videos
        }
        return VideoDAO.getById(id)
    }


}
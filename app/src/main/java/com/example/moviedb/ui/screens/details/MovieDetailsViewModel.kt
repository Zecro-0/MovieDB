package com.example.moviedb.ui.screens.details

import android.text.format.Time
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.moviedb.MovieDBApplication
import com.example.moviedb.data.IMovieRepository
import com.example.moviedb.model.Genre
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieDetailResponse
import com.example.moviedb.model.Review
import com.example.moviedb.model.Video
import com.example.moviedb.ui.screens.list.ListUiState
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

sealed interface MovieDetailsUiState {
    data class Success(val movie: MovieDetailResponse, val genres: List<Genre>, val reviews: List<Review>, val videos: List<Video>) : MovieDetailsUiState
    object Error : MovieDetailsUiState
    object Loading : MovieDetailsUiState
    object Disconnected : MovieDetailsUiState
}

class MovieDetailsViewModel(private val movieRepository: IMovieRepository, private val movieId: Int) : ViewModel() {
    var uiState: MovieDetailsUiState by mutableStateOf(MovieDetailsUiState.Loading)
        private set

    companion object {
        fun Factory(movieId: Int): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieDBApplication)
                MovieDetailsViewModel(
                    movieRepository = application.container.movieRepository,
                    movieId = movieId
                )
            }
        }
    }



    fun getMovie(){
        viewModelScope.launch {
            uiState = MovieDetailsUiState.Loading
            uiState = try {
                val genres = movieRepository.getMovieAndTvGenres()
                val result = movieRepository.getMovie(movieId)
                val reviews = movieRepository.getReviews(movieId)
                val videos = movieRepository.getVideos(movieId)
                MovieDetailsUiState.Success(result, genres = genres.genres, reviews.results, videos.results)
            }catch (e: IllegalStateException) {
                Log.e("MovieListScreenViewModel", e.toString())
                MovieDetailsUiState.Disconnected
            }
            catch (e: Exception) {
                Log.e("MovieListScreenViewModel", e.toString())
                MovieDetailsUiState.Error
            }
        }
    }
}
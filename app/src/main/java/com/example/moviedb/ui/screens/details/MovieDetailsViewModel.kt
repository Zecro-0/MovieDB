package com.example.moviedb.ui.screens.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviedb.MovieDBApplication
import com.example.moviedb.data.IMovieRepository
import com.example.moviedb.model.Genre
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieDetailResponse
import com.example.moviedb.model.Review
import com.example.moviedb.model.Video
import com.example.moviedb.ui.screens.list.ListUiState
import kotlinx.coroutines.launch

sealed interface MovieDetailsUiState {
    data class Success(val movie: MovieDetailResponse, val genres: List<Genre>, val reviews: List<Review>, val videos: List<Video>) : MovieDetailsUiState
    object Error : MovieDetailsUiState
    object Loading : MovieDetailsUiState
}

class MovieDetailsViewModel(private val movieRepository: IMovieRepository, private val movieId: Int) : ViewModel() {
    var uiState: MovieDetailsUiState by mutableStateOf(MovieDetailsUiState.Loading)
        private set

    init {
        getMovie()
    }

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



    private fun getMovie(){
        viewModelScope.launch {
            uiState = MovieDetailsUiState.Loading
            uiState = try {
                val moveGenres = movieRepository.getMovieGenres()
                val tvGenres = movieRepository.getTvGenres()
                val result = movieRepository.getMovie(movieId)
                val reviews = movieRepository.getReviews(movieId)
                val videos = movieRepository.getVideos(movieId)
                MovieDetailsUiState.Success(result, moveGenres.genres + tvGenres.genres, reviews.results, videos.results)
            } catch (e: Exception) {
                Log.e("MovieListScreenViewModel", e.toString())
                MovieDetailsUiState.Error
            }
        }
    }
}
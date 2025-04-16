package com.example.moviedb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviedb.data.HardCodedMovieRepository
import com.example.moviedb.data.IMovieRepository
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieDetails
import com.example.moviedb.ui.screens.list.MovieListScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MovieDbAppViewModel(private val _movieRepository: IMovieRepository): ViewModel() {
    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie.asStateFlow() // Updated to use asStateFlow()
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
//                val application = (this[APPLICATION_KEY] as MovieDBApplication)
                MovieDbAppViewModel(_movieRepository = HardCodedMovieRepository())
            }
        }
    }
    fun setSelectedMovie(movie: Movie?) {
        _selectedMovie.value = movie
    }

    fun clearSelectedMovie() {
        _selectedMovie.value = null
    }

    fun getSelectedMovieDetails(): MovieDetails?{
        var movieDetails: MovieDetails? = null
        viewModelScope.launch {
            if (_selectedMovie.value != null) {
                movieDetails = _movieRepository.getMovie(_selectedMovie.value!!.id)
            } else{
                movieDetails = null
            }
        }
        return movieDetails
    }


}
package com.example.moviedb.ui.screens.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moviedb.MovieDBApplication
import com.example.moviedb.data.HardCodedMovieRepository
import com.example.moviedb.data.IMovieRepository
import com.example.moviedb.model.Movie
import kotlinx.coroutines.launch

sealed interface ListUiState {
    data class Success(val movies: List<Movie>) : ListUiState
    object Error : ListUiState
    object Loading : ListUiState
}

class MovieListScreenViewModel(private val movieRepository: IMovieRepository) : ViewModel() {
    var uiState: ListUiState by mutableStateOf(ListUiState.Loading)
        private set

    init {
        getMovies()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
//                val application = (this[APPLICATION_KEY] as MovieDBApplication)
                MovieListScreenViewModel(movieRepository = HardCodedMovieRepository())
            }
        }
    }

    fun getMovies(){
        viewModelScope.launch {
            uiState = ListUiState.Loading
            uiState = try {
                val result = movieRepository.getMovies()
                ListUiState.Success(result)
            } catch (e: Exception) {
                Log.e("MovieListScreenViewModel", e.toString())
                ListUiState.Error
            }
        }
    }
}
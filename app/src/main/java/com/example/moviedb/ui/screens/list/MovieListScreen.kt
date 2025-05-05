package com.example.moviedb.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviedb.model.Genre
import com.example.moviedb.model.Movie


@Composable
fun MovieListScreen(uiState: ListUiState, onClick: (movie: Movie) -> Unit, modifier: Modifier = Modifier) {
    when (uiState) {
        is ListUiState.Loading -> Text("loading...")
        is ListUiState.Success -> MovieGrid(movies = uiState.movies, genres = uiState.genres, onClick = onClick, modifier = modifier)
        is ListUiState.Error -> Text("error")
        is ListUiState.Disconnected -> Text("Disconnected")
    }
}

@Composable
fun MovieGrid(movies: List<Movie>, genres: List<Genre>, onClick: (movie: Movie) -> Unit, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ){
        items(movies){ movie ->
            MovieCard(movie = movie, genrers = genres, onClick = onClick)
        }
    }
}

@Composable
fun MovieList(movies: List<Movie>, genres: List<Genre>, onClick: (movie: Movie) -> Unit,modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        items(movies) { movie ->
            MovieCard(movie = movie, genrers = genres, onClick = onClick)
        }
    }
}


@Composable
fun MovieCard(movie: Movie,genrers: List<Genre>, onClick: (movie: Movie) -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = { onClick(movie) },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = movie.title,
            modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = "Genres:"
            )
            LazyRow {
                items(movie.genreIds){
                    MovieGenreChip(genre = genrers.first { genre -> genre.id == it })
                }
            }
        }

    }
}

@Composable
fun MovieGenreChip(genre: Genre, modifier: Modifier = Modifier){
    SuggestionChip(
        label = { Text(text = genre.name) },
        onClick = {},
        modifier = modifier.padding(start = 8.dp)
    )
}


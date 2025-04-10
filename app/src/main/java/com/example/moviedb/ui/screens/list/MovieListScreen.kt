package com.example.moviedb.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviedb.data.HardCodedMovieRepository
import com.example.moviedb.model.Genre
import com.example.moviedb.model.Movie


@Composable
fun MovieListScreen(uiState: ListUiState, onClick: (movie: Movie) -> Unit, modifier: Modifier = Modifier) {
    when (uiState) {
        is ListUiState.Loading -> Text("loading...")
        is ListUiState.Success -> MovieList(movies = uiState.movies, onClick = onClick, modifier = modifier)
        is ListUiState.Error -> Text("error")

    }
}

@Composable
fun MovieList(movies: List<Movie>, onClick: (movie: Movie) -> Unit,modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        items(movies) { movie ->
            MovieCard(movie = movie, onClick = onClick)
        }
    }
}


@Composable
fun MovieCard(movie: Movie, onClick: (movie: Movie) -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = { onClick(movie) },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = movie.name,
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
                items(movie.genres){
                    MovieGenreChip(it)
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

@Preview(showBackground = true)
@Composable
fun MovieCardPreview() {
    MovieCard(
        onClick =  {},
        movie = Movie(
            adult = false,
            backdropPath = "/qFfWFwfaEHzDLWLuttWiYq7Poy2.jpg",
            genreIds = listOf(10767),
            id = 2261,
            originCountry = listOf("US"),
            originalLanguage = "en",
            originalName = "The Tonight Show Starring Johnny Carson",
            overview = "The Tonight Show Starring Johnny Carson is a talk show hosted by Johnny Carson under The Tonight Show franchise from 1962 to 1992. It originally aired during late-night. For its first ten years, Carson's Tonight Show was based in New York City with occasional trips to Burbank, California; in May 1972, the show moved permanently to Burbank, California. In 2002, The Tonight Show Starring Johnny Carson was ranked #12 on TV Guide's 50 Greatest TV Shows of All Time.",
            popularity = 667.7367,
            posterPath = "/oA8QVTGlAN511uCAMDN60aVQUs1.jpg",
            firstAirDate = "1962-10-01",
            name = "The Tonight Show Starring Johnny Carson",
            voteAverage = 7.41,
            voteCount = 72,
            genres = listOf(
                Genre(
                    id = 10767,
                    name = "Talk"
                )
            )

        )
    )
}
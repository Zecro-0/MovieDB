package com.example.moviedb.ui.screens.details

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.example.moviedb.model.Genre
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieDetailResponse
import com.example.moviedb.model.Review
import com.example.moviedb.model.Video
import com.example.moviedb.ui.screens.list.MovieGenreChip

@Composable
fun DetailsScreen(
    viewModel: MovieDetailsViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    when(viewModel.uiState){
        is MovieDetailsUiState.Loading -> Text("loading...")
        MovieDetailsUiState.Error -> TODO()
        is MovieDetailsUiState.Success -> MovieDetails(
            movie = (viewModel.uiState as MovieDetailsUiState.Success).movie,
            genres = (viewModel.uiState as MovieDetailsUiState.Success).genres,
            reviews = (viewModel.uiState as MovieDetailsUiState.Success).reviews,
            videos = (viewModel.uiState as MovieDetailsUiState.Success).videos,
            modifier = modifier,
            scrollState = scrollState)
    }
}

@Composable
fun MovieDetails(
    movie: MovieDetailResponse,
    genres: List<Genre>,
    reviews: List<Review>,
    videos: List<Video>,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
){
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        // Movie poster and title section
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = "Poster for ${movie.title}",
                modifier = Modifier
                    .size(150.dp)
                    .padding(end = 16.dp)
            )

            Column {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                if (movie.originalTitle != movie.title) {
                    Text(
                        text = movie.originalTitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                }

                Text(
                    text = "Released: ${movie.releaseDate}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD700)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${movie.voteAverage} (${movie.voteCount} votes)",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Genres",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow {
            items(movie.genres) { genre ->
                MovieGenreChip(genre = genres.first { it.id == genre.id })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Overview",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Reviews",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        LazyRow {
            items(reviews){
                    review -> ReviewCard(review = review, modifier = modifier.widthIn(max = 300.dp))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Videos",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        LazyRow {
            items(videos){
                    video -> MovieVideoCard(video = video, modifier = modifier.widthIn(max = 300.dp))
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Additional Information",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Language: ${movie.originalLanguage.uppercase()}")
        Text(text = "Popularity: ${movie.popularity}")
        if (movie.originCountry.isNotEmpty()) {
            Text(text = "Country: ${movie.originCountry.joinToString(", ")}")
        }

        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(vertical = 16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = {
                    uriHandler.openUri("https://www.themoviedb.org/tv/${movie.id}")
                },
                modifier = modifier
            ) {
                Text(
                    text = "View on TMDB",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    uriHandler.openUri("https://www.imdb.com/title/${movie.imdbId}")
                },
                modifier = modifier,
            ){
                Text(
                    text = "View on IMDB",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }

    }
}

@Composable
fun ReviewCard(review: Review, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = review.author,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = review.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Composable
fun MovieVideoCard(video: Video, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaSource = MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")

    // Set MediaSource to ExoPlayer
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    // Manage lifecycle events
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = video.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
                )
                Text(
                    text = if(video.official) "Official: ${video.type}" else "Unofficial: ${video.type}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
                )


            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        player = exoPlayer
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Set your desired height
            )
        }

    }
}



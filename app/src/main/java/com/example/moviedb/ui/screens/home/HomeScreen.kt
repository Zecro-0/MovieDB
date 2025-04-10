package com.example.moviedb.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreen(
    navigateToNextScreen: () -> Unit,
    modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Movie DB",
            modifier = modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "A app created for the course M7019E",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "by Adrian Fingal, Oscar Johansson and Hugo Hedlund",
            style = MaterialTheme.typography.headlineLarge
        )
        Button(
            onClick = navigateToNextScreen
        ) {
            Text(text = "Start")
        }
    }
}
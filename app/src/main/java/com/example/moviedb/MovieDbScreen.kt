package com.example.moviedb


import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviedb.ui.screens.home.HomeScreen
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviedb.ui.screens.list.MovieListScreen
import com.example.moviedb.ui.screens.list.MovieListScreenViewModel
import com.example.moviedb.ui.screens.details.DetailsScreen
import com.example.moviedb.ui.screens.details.MovieDetailsViewModel


enum class MovieDbScreen(val title: String?) {
    Home(title = null),
    List(title = "Movies"),
    Detail(title = "Movie Detail")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDBAppBar(
    currentScreen: MovieDbScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.screen_title, currentScreen.title ?: ""))
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MovieDbApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: MovieDbAppViewModel = viewModel(factory = MovieDbAppViewModel.Factory)
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MovieDbScreen.valueOf(
        backStackEntry?.destination?.route ?: MovieDbScreen.Home.name
    )

    Scaffold(
        topBar = {
            MovieDBAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MovieDbScreen.Home.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = MovieDbScreen.Home.name) {
                HomeScreen(
                    navigateToNextScreen = {
                        navController.navigate(MovieDbScreen.List.name)
                    },
                    modifier = modifier
                )
            }

            composable(
                route = MovieDbScreen.List.name,
            ) {
                val listViewModel: MovieListScreenViewModel =
                    viewModel(factory = MovieListScreenViewModel.Factory)
                MovieListScreen(listViewModel.uiState, onClick = { movie ->
                    viewModel.setSelectedMovie(movie)
                    navController.navigate(MovieDbScreen.Detail.name)
                }, modifier = modifier)
            }

            composable(
                route = MovieDbScreen.Detail.name,
            ){
                val detailsViewModel: MovieDetailsViewModel =
                    viewModel(factory = MovieDetailsViewModel.Factory(viewModel.selectedMovie.collectAsStateWithLifecycle().value!!.id) )
                DetailsScreen(viewModel= detailsViewModel, modifier=modifier)
            }
        }
    }
}
package com.example.moviemania

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviemania.*
import com.example.moviemania.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Define a ViewModel to handle fetching movies
class MovieListViewModel : ViewModel() {
    private val _movies = mutableStateOf<List<Movie>>(emptyList())
    val movies = _movies.value

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            val fetchedMovies = NetworkClient.getPopularMovies()
            _movies.value = fetchedMovies ?: emptyList()
        }
    }
}

@Composable
fun MovieListScreen(viewModel: MovieListViewModel = viewModel()) {
    val movies = viewModel.movies

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Movie List") }) }
    ) {
        MovieList(movies)
    }
}

@Composable
fun MovieList(movies: List<Movie>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies) { movie ->
            MovieListItem(movie)
        }
    }
}

@Composable
fun MovieListItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = movie.title)
            Text(text = movie.overview, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesAppTheme {
        MovieListScreen()
    }
}

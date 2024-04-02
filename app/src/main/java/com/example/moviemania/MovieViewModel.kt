package com.example.moviemania

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val movieDataProvider: MovieDataProvider) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>?>()
    val movies: MutableLiveData<List<Movie>?> = _movies

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedMovies = movieDataProvider.fetchMovies()
            _movies.postValue(fetchedMovies)
        }
    }
}

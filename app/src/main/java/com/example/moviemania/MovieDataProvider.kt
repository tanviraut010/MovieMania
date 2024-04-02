package com.example.moviemania

import android.util.Log
import com.example.network.NetworkClient
import data.Movie

class MovieDataProvider(private val networkClient: NetworkClient) {
    suspend fun fetchMovies(): List<Movie>? {
        return try {
            networkClient.getPopularMovies()
        } catch (e: Exception) {
            Log.d("MovieDataProvider", "fetchMovies:error: ${e.message} ")
            null
        }
    }
}

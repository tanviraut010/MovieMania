package com.example.network

import data.Movie
import data.MovieListResponse
import network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "909594533c98883408adef5d56143539"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    suspend fun getPopularMovies(): List<Movie>? {
        val response = apiService.getPopularMovies(API_KEY)
        return if (response.isSuccessful) {
            response.body()?.results
        } else {
            null
        }
    }
}

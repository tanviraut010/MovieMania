package com.example.moviemania

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.network.NetworkClient

class MainActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(MovieDataProvider(NetworkClient))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieFragment = MovieFragment.newInstance(movieViewModel)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, movieFragment)
            .commit()
    }
}
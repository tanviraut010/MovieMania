package com.example.moviemania

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import data.Movie

class MovieAdapter(
    private val context: Context,
    var movies: List<Movie>,
    private val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            onItemClick.onItemClickListener(
                movie.title, movie.releaseDate, movie.overview, movie.posterPath
            )
        }
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewMoviePoster: ImageView =
            itemView.findViewById(R.id.imageViewMoviePoster)
        private val textViewMovieTitle: TextView = itemView.findViewById(R.id.textViewMovieTitle)

        fun bind(movie: Movie) {
            Glide.with(imageViewMoviePoster)
                .load("https://image.tmdb.org/t/p/w220_and_h330_face/${movie.posterPath}")
                .fitCenter()
                .into(imageViewMoviePoster)
            textViewMovieTitle.text = movie.title
        }
    }
}

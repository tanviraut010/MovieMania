package com.example.moviemania

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
class MovieDetailFragment : Fragment() {

    private var title: String? = ""
    private var releaseDate: String? = ""
    private var overview: String? = ""
    private var posterPath: String? = ""

    private lateinit var titleTextView: TextView
    private lateinit var releaseDateTextView: TextView
    private lateinit var overviewTextView: TextView
    private lateinit var posterImageView: ImageView

    companion object {
        fun newInstance(bundle: Bundle = Bundle()): MovieDetailFragment {
            val fragment = MovieDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        titleTextView = view.findViewById(R.id.titleTextView)
        releaseDateTextView = view.findViewById(R.id.releaseDateTextView)
        overviewTextView = view.findViewById(R.id.overviewTextView)
        posterImageView = view.findViewById(R.id.posterImageView)
        return view
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        title = arguments?.getString("title", "")
        releaseDate = arguments?.getString("releaseDate", "")
        overview = arguments?.getString("overView", "")
        posterPath = arguments?.getString("posterPath", "")

        Glide.with(posterImageView)
            .load("https://image.tmdb.org/t/p/w220_and_h330_face/${posterPath}")
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(posterImageView)

        titleTextView.text = title
        releaseDateTextView.text = releaseDate
        overviewTextView.text = overview
    }
}
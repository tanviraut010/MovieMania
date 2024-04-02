package com.example.moviemania

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MovieFragment : Fragment(), OnItemClick {

    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    companion object {
        fun newInstance(movieViewModel: MovieViewModel): MovieFragment {
            val fragment = MovieFragment()
            fragment.movieViewModel = movieViewModel
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewMovieList)
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter(requireContext(), emptyList(), this)
        recyclerView.adapter = movieAdapter

        movieViewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (movies != null) {
                movieAdapter.movies = movies
            }
            movieAdapter.notifyDataSetChanged()
        }

        movieViewModel.fetchMovies()

    }

    override fun onItemClickListener(
        title: String?,
        releaseDate: String?,
        overView: String?,
        posterPath: String?
    ) {

        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putString("releaseDate", releaseDate)
        bundle.putString("overView", overView)
        bundle.putString("posterPath", posterPath)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MovieDetailFragment.newInstance(bundle))
            .addToBackStack("").commit()
    }
}

package com.example.moviemania

interface OnItemClick {
    fun onItemClickListener(
        title: String?,
        releaseDate: String?,
        overView: String?,
        posterPath: String?
    )
}
package com.example.lab4movie.data

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val voteAverage: Double
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"
    
    val backdropUrl: String
        get() = "https://image.tmdb.org/t/p/original$backdropPath"
}

data class MovieResponse(
    val results: List<Movie>
) 
package com.example.lab4movie.data

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Double
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"
    
    val backdropUrl: String
        get() = "https://image.tmdb.org/t/p/original$backdropPath"
}

data class MovieResponse(
    val results: List<Movie>
) 
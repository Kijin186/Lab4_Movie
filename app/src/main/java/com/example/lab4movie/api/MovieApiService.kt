package com.example.lab4movie.api

import com.example.lab4movie.data.Movie
import com.example.lab4movie.data.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Query("api_key") apiKey: String,
        @Query("movie_id") movieId: Int
    ): Movie
}

object MovieApi {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    
    fun create(): MovieApiService {
        return retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }
} 
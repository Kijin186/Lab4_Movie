package com.example.lab4movie

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.lab4movie.api.MovieApi
import com.example.lab4movie.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getIntExtra("movie_id", -1)
        if (movieId == -1) {
            Toast.makeText(this, "Invalid movie ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        try {
            fetchMovieDetails(movieId)
        } catch (e: Exception) {
            Toast.makeText(this, "Error loading movie details", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun fetchMovieDetails(movieId: Int) {
        val apiKey = "b2a8055058c8dc2222aff9b9fbe1bab0"
        val api = MovieApi.create()
        lifecycleScope.launch {
            try {
                val movie: Movie = api.getMovieDetails(movieId, apiKey)
                showMovieDetails(movie)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MovieDetailActivity, "Failed to load details: ${e.message}", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    private fun showMovieDetails(movie: Movie) {
        try {
            val poster = findViewById<ImageView>(R.id.detailPoster)
            val title = findViewById<TextView>(R.id.detailTitle)
            val overview = findViewById<TextView>(R.id.detailOverview)
            val releaseDate = findViewById<TextView>(R.id.detailReleaseDate)
            val rating = findViewById<TextView>(R.id.detailRating)

            Glide.with(this)
                .load(movie.posterUrl)
                .error(R.drawable.ic_launcher_background)
                .into(poster)
            
            title.text = movie.title
            overview.text = movie.overview
            releaseDate.text = "Release Date: ${movie.releaseDate}"
            rating.text = "Rating: ${movie.voteAverage}"
        } catch (e: Exception) {
            Toast.makeText(this, "Error displaying movie details", Toast.LENGTH_SHORT).show()
        }
    }
} 
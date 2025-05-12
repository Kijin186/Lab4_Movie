package com.example.lab4movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab4movie.api.MovieApi
import com.example.lab4movie.data.Movie
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val api = MovieApi.create()
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> = _selectedMovie

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = api.getPopularMovies(apiKey)
                _movies.value = response.results
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectMovie(movie: Movie) {
        _selectedMovie.value = movie
    }
} 
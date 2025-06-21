package com.example.katalogfilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogfilm.data.remote.Movie
import com.example.katalogfilm.data.remote.MovieApiService
import com.example.katalogfilm.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    private val api: MovieApiService = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApiService::class.java)

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                val response = api.getPopularMovies(Constants.API_KEY)
                _movies.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

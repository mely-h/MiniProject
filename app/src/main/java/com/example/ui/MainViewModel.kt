package com.example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Movie
import com.example.model.SingleResponse
import com.example.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MoviesViewModel : ViewModel() {
    private val MoviesApi: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    private val _allMovies = MutableLiveData<List<Movie>>()
    val allMovies: LiveData<List<Movie>> = _allMovies

    fun loadAllMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MoviesApi.getAllMovies("c6f38076699aa54f96b9750db2bcdf8d")
                if (response.isSuccessful && response.body() != null) {
                    _allMovies.postValue(response.body()!!.results)
                } else {
                    _allMovies.postValue(emptyList())
                }
            } catch (e: Exception) {
                _allMovies.postValue(emptyList())
            }
        }
    }
}



class DetailsViewModel : ViewModel() {
    private val movieApi: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    private val _movieDetails = MutableLiveData<SingleResponse>()
    val movieDetails: LiveData<SingleResponse> = _movieDetails


    fun loadMovieDetails(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = movieApi.getMovie(movieId, "c6f38076699aa54f96b9750db2bcdf8d")
                if (response.isSuccessful && response.body() != null) {
                    _movieDetails.postValue(response.body()!!)
                }
            } catch (e: Exception) {

            }
        }
    }
}

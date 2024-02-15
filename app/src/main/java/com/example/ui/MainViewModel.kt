package com.example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Movie
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

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> = _popularMovies

    fun loadPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MoviesApi.getPopularMovies("c6f38076699aa54f96b9750db2bcdf8d")
                if (response.isSuccessful && response.body() != null) {
                    _popularMovies.postValue(response.body()!!.results)
                } else {
                    // Gestion des erreurs, par exemple en affichant un message d'erreur ou en mettant à jour un état d'erreur dans le ViewModel
                    _popularMovies.postValue(emptyList())
                }
            } catch (e: Exception) {
                // Gestion des exceptions, par exemple en affichant un message d'erreur ou en mettant à jour un état d'erreur dans le ViewModel
                _popularMovies.postValue(emptyList())
            }
        }
    }
}
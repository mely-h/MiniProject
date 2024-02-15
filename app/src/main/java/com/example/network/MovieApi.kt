package com.example.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.model.MovieResponse
import com.example.model.SingleResponse
import retrofit2.http.Path

interface MovieApi {

    @GET("movie/popular")
   suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieResponse>

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") movieId: String, @Query("api_key") apiKey: String): Response<SingleResponse>


    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}


package com.example.model


data class MovieResponse(
    val results: List<Movie>
)

data class SingleResponse(
    val title: String,
    val overview: String,
    val poster_path: String,
    val id: Int
)
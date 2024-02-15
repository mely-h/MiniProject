package com.example.myapplication

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.Single
import com.example.ui.DetailsViewModel


@Composable
fun MovieDetailScreen( movieId: String, viewModel: DetailsViewModel = viewModel()) {
    val movieDetails by viewModel.movieDetails.observeAsState()

    LaunchedEffect(movieId) {
        viewModel.loadMovieDetails(movieId)
    }

    // Affichage des détails du film
    movieDetails?.let { single ->
        Text(text = single.title)

    } ?: Text("Chargement des détails du film...")

}





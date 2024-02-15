package com.example.myapplication
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Card
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.model.Movie
import com.example.ui.MoviesViewModel






@Composable
fun MoviesDisplay(navController: NavController) {
    val movieViewModel: MoviesViewModel = viewModel()
    val movies = movieViewModel.allMovies.observeAsState(initial = emptyList()).value
    LaunchedEffect(key1 = true) {
        movieViewModel.loadAllMovies()
    }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(movies) { movie ->
            MovieItem(movie = movie) {
                navController.navigate("movieDetail/${movie.id}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(movie: Movie  ,  onClick: () -> Unit ) {
    val imageUrl = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"
    Log.d(TAG, "MovieItem: $imageUrl")

    Card(  onClick = onClick , modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = imageUrl)
                        .allowHardware(false)
                        .build()
                ),
                contentDescription = "Movie image",
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
            Text(text = movie.title, style = MaterialTheme.typography.headlineMedium)
        }
    }
}
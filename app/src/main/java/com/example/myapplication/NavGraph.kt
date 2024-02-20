package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Connection.route) {
        composable(Screen.Connection.route) { Connection(navController) }
        composable(Screen.MovieDisplay.route) { MoviesDisplay(navController) }
        composable(Screen.Favorite.route) { Favorite(navController) }
        composable("movieDetail/{movieId}") { backStackEntry ->
            MovieDetailScreen(movieId = backStackEntry.arguments?.getString("movieId") ?: "")
        }
    }
}



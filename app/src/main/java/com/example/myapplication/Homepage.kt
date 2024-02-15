// HomeScreen.kt
package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.model.VideoCategory


@Composable
fun HomeScreen(categories: List<VideoCategory>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(categories) { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun CategoryItem(category: VideoCategory) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(
            text = category.name,
        )
    }
}


package com.example.myapplication
import androidx.activity.compose.setContent
import com.example.myapplication.HomeScreen
import com.example.model.VideoCategory

import android.os.Bundle
import androidx.activity.ComponentActivity



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val categories = listOf(
                VideoCategory("Actions", listOf("Video 1", "Video 2")),
                VideoCategory("Comédies", listOf("Video 3", "Video 4"))
            )
            HomeScreen(categories = categories)
        }
    }
}

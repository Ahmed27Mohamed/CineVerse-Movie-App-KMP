package com.a2004256_ahmedmohamed.movieapp.presentation.movie

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.web.dom.Iframe

@Composable
actual fun YouTubePlayer(videoKey: String, modifier: Modifier) {
    val youtubeUrl = "https://www.youtube.com/embed/$videoKey"
    Iframe(
        attrs = {
            attr("src", youtubeUrl)
            attr("allowfullscreen", "true")
            attr("style", "width: 100%; height: 100%; border: none;")
            attr("referrerpolicy", "strict-origin-when-cross-origin")
        }
    )
}
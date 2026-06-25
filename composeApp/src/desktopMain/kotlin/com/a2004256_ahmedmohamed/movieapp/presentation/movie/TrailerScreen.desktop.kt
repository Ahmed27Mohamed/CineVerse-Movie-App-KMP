package com.a2004256_ahmedmohamed.movieapp.presentation.movie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import java.awt.Desktop
import java.net.URI

@Composable
actual fun YouTubePlayer(videoKey: String, modifier: Modifier) {
    val url = "https://www.youtube.com/watch?v=$videoKey"
    LaunchedEffect(videoKey) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(URI(url))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
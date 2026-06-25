package com.a2004256_ahmedmohamed.movieapp.presentation.movie

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData

@Composable
actual fun YouTubePlayer(videoKey: String, modifier: Modifier) {
    val html = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
            <meta name="referrer" content="strict-origin-when-cross-origin" />
            <style>
                body { margin: 0; padding: 0; background-color: #000; display: flex; justify-content: center; align-items: center; height: 100vh; overflow: hidden; }
                iframe { width: 100vw; height: 100vh; border: none; }
            </style>
        </head>
        <body>
            <iframe 
                src="https://www.youtube.com/embed/$videoKey?autoplay=1&controls=1" 
                allow="autoplay; encrypted-media; fullscreen" 
                allowfullscreen 
                referrerpolicy="strict-origin-when-cross-origin">
            </iframe>
        </body>
        </html>
    """.trimIndent()
    val webViewState = rememberWebViewStateWithHTMLData(
        data = html,
        baseUrl = "https://www.google.com"
    )
    WebView(
        state = webViewState,
        modifier = modifier
    )
}
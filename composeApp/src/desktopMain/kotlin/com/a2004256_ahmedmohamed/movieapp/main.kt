package com.a2004256_ahmedmohamed.movieapp

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor2.KtorNetworkFetcherFactory
import com.a2004256_ahmedmohamed.movieapp.di.appModule
import org.koin.core.context.GlobalContext.startKoin

fun main() = application {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                add(KtorNetworkFetcherFactory())
            }
            .build()
    }
    startKoin {
        modules(appModule)
    }
        Window(
            onCloseRequest = ::exitApplication,
            title = "CineVerse",
        ) {
            App()
        }
}
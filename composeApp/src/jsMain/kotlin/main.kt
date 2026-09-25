package com.a2004256_ahmedmohamed.movieapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.a2004256_ahmedmohamed.movieapp.di.initKoin
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    Firebase.initialize(
        options = FirebaseOptions(
            applicationId = "",
            apiKey = "",
            projectId = ""
        )
    )

    requestWebNotificationPermission()

    initKoin()

    onWasmReady {
        CanvasBasedWindow(canvasElementId = "ComposeTarget") {
            App()
        }
    }
}

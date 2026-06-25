package com.a2004256_ahmedmohamed.movieapp

import androidx.compose.ui.Modifier

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.a2004256_ahmedmohamed.movieapp.presentation.ai

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val movie: Movie? = null
)

data class Movie(
    val title: String,
    val description: String,
    val imageUrl: String,
    val rating: Double,
    val genre: String
)
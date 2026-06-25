package com.a2004256_ahmedmohamed.movieapp.presentation.stats

data class Stats(
    val totalWatched: Int = 0,
    val watchTimeMinutes: Int = 0,
    val cineRank: String = "Rookie",
    val weekly: List<Float> = listOf(0f,0f,0f,0f,0f,0f,0f),
    val genres: List<Pair<String, Float>> = listOf(
        "Sci-Fi" to 0.34f,
        "Drama" to 0.22f,
        "Action" to 0.18f,
        "Thriller" to 0.15f,
        "Other" to 0.11f
    )
)
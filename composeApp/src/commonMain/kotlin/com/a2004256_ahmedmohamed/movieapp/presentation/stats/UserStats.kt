package com.a2004256_ahmedmohamed.movieapp.presentation.stats

data class UserStats(
    val totalWatched: Int = 0,
    val watchTimeDays: Double = 0.0,
    val cineRank: String = "",
    val weeklyWatchTime: Map<String, Int> = emptyMap(),
    val genres: Map<String, Int> = emptyMap()
)
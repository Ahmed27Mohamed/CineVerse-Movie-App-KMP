package com.a2004256_ahmedmohamed.movieapp.presentation.watchlist

import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import kotlinx.serialization.Serializable

@Serializable
data class WatchlistEntity(
    val id: Int = 0,
    val title: String = "",
    val imageUrl: String = "",
    val rating: Double = 0.0
)
fun WatchlistEntity.toMovieFixed(): Movie {
    return Movie(
        id = id,
        title = title,
        imageUrl = imageUrl,
        rating = rating,
        description = "",
        runtime = 0,
        ageRating = "",
        cast = emptyList(),
        similarMovies = emptyList()
    )
}
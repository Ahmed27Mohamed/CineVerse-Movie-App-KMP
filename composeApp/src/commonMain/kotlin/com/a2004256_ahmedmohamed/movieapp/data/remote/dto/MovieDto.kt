package com.a2004256_ahmedmohamed.movieapp.data.remote.dto

import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val original_title: String? = null,
    val original_language: String? = null,
    val overview: String,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    val vote_average: Double? = null
)
fun MovieDto.toMovie(): Movie {
    val titleToShow = if (original_language == "ar") original_title ?: title else title
    val imageBaseUrl = "https://image.tmdb.org/t/p/w500"
    val imageUrl = poster_path?.let {
        "$imageBaseUrl$it"
    } ?: ""
    return Movie(
        id = id,
        title = titleToShow,
        description = overview,
        rating = vote_average ?: 0.0,
        imageUrl = imageUrl,
        runtime = 0,
        ageRating = "N/A",
        cast = emptyList(),
        similarMovies = emptyList()
    )
}
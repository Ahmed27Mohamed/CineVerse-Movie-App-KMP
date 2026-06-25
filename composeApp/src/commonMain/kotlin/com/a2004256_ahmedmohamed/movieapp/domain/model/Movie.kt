package com.a2004256_ahmedmohamed.movieapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val rating: Double,
    val imageUrl: String,
    val runtime: Int,
    val ageRating: String,
    val cast: List<Cast>,
    val similarMovies: List<Movie>
)
data class Cast(
    val id: Int,
    val name: String,
    val character: String,
    val imageUrl: String
)
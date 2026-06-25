package com.a2004256_ahmedmohamed.movieapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDto(
    val title: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val vote_average: Double? = null,
    val runtime: Int? = null
)
@Serializable
data class CreditsDto(
    val cast: List<CastDto>
)
@Serializable
data class CastDto(
    val id: Int,
    val name: String,
    val character: String,
    val profile_path: String? = null
)
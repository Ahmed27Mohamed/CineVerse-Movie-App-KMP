package com.a2004256_ahmedmohamed.movieapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val page: Int? = null,
    val results: List<MovieDto> = emptyList(),
    val total_pages: Int? = null,
    val total_results: Int? = null
)
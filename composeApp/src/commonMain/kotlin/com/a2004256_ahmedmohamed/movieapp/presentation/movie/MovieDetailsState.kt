package com.a2004256_ahmedmohamed.movieapp.presentation.movie

import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie

data class MovieDetailsState(
    val movie: Movie? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
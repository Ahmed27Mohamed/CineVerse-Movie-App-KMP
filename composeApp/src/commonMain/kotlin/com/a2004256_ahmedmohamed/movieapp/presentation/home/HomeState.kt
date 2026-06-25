package com.a2004256_ahmedmohamed.movieapp.presentation.home

import com.a2004256_ahmedmohamed.movieapp.data.remote.dto.MovieDto
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie

data class HomeState(

    val trendingMovies: List<Movie> = emptyList(),

    val popularMovies: List<Movie> = emptyList(),

    val topRatedMovies: List<Movie> = emptyList(),

    val upcomingMovies: List<Movie> = emptyList(),

    val isLoading: Boolean = false,

    val error: String? = null
)
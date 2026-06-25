package com.a2004256_ahmedmohamed.movieapp.domain.repository

import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import com.a2004256_ahmedmohamed.movieapp.domain.model.Trailer

interface MovieRepository {

    suspend fun getTrendingMovies(): List<Movie>

    suspend fun getPopularMovies(): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getUpcomingMovies(): List<Movie>

    suspend fun getMovieDetails(id: Int): Movie

    suspend fun getTrailer(movieId: Int): Trailer?

}
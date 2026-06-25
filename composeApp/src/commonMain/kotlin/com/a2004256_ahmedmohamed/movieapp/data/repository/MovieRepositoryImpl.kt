package com.a2004256_ahmedmohamed.movieapp.data.repository

import com.a2004256_ahmedmohamed.movieapp.data.remote.MovieApi
import com.a2004256_ahmedmohamed.movieapp.data.remote.dto.toMovie
import com.a2004256_ahmedmohamed.movieapp.domain.model.Cast
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import com.a2004256_ahmedmohamed.movieapp.domain.model.Trailer
import com.a2004256_ahmedmohamed.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: MovieApi
) : MovieRepository {

    suspend fun searchMovies(query: String): List<Movie> {
        return api.searchMovies(query).map {
            it.toMovie()
        }
    }

    override suspend fun getTrendingMovies() =
        api.getTrending().map {
            it.toMovie()
        }

    override suspend fun getPopularMovies() =
        api.getPopular().map {
            it.toMovie()
        }

    override suspend fun getTopRatedMovies() =
        api.getTopRated().map {
            it.toMovie()
        }

    override suspend fun getUpcomingMovies() =
        api.getUpcoming().map {
            it.toMovie()
        }

    override suspend fun getTrailer(movieId: Int): Trailer? {
        val response = api.getMovieVideos(movieId)
        val trailer = response.results.firstOrNull {
            it.site == "YouTube" && it.type == "Trailer"
        }
        return trailer?.let {
            Trailer(
                key = it.key,
                name = it.name
            )
        }
    }

    override suspend fun getMovieDetails(id: Int): Movie {

        val movieDetails = api.getMovieDetails(id)

        val credits = api.getMovieCredits(id)

        val similar = api.getSimilarMovies(id)

        return Movie(
            id = id,
            title = movieDetails.original_title ?: "",
            description = movieDetails.overview ?: "",
            rating = movieDetails.vote_average ?: 0.0,
            imageUrl =
                "https://image.tmdb.org/t/p/w500${movieDetails.poster_path ?: ""}",

            runtime = movieDetails.runtime ?: 0,

            ageRating = "PG-13",

            cast = credits.cast.map {

                Cast(
                    id = it.id,
                    name = it.name,
                    character = it.character,
                    imageUrl =
                        "https://image.tmdb.org/t/p/w500${it.profile_path ?: ""}"
                )
            },

            similarMovies = similar.map {
                it.toMovie()
            }
        )
    }
}
package com.a2004256_ahmedmohamed.movieapp.data.remote

import com.a2004256_ahmedmohamed.movieapp.data.remote.dto.CreditsDto
import com.a2004256_ahmedmohamed.movieapp.data.remote.dto.MovieDetailsDto
import com.a2004256_ahmedmohamed.movieapp.data.remote.dto.MovieDto
import com.a2004256_ahmedmohamed.movieapp.data.remote.dto.MoviesResponse
import com.a2004256_ahmedmohamed.movieapp.data.remote.dto.VideosResponse
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApi(private val client:HttpClient){
    private val apiKey="b05d2221ed0360a87fc8af0d438e1ec2"
    private val baseUrl= "https://api.themoviedb.org/3"
    suspend fun searchMovies(query: String): List<MovieDto> {
        val response = client.get("https://api.themoviedb.org/3/search/movie") {
            parameter("api_key", apiKey)
            parameter("query", query)
            parameter("language", "en-US")
        }.body<MoviesResponse>()
        return response.results
    }
    suspend fun getMovieVideos(movieId: Int): VideosResponse {
        return client.get("$baseUrl/movie/$movieId/videos") {
            parameter("api_key", apiKey)
        }.body()
    }
    suspend fun getTrending():List<MovieDto>{
        val response=
            client.get(
                "$baseUrl/trending/movie/day"
            ){
                parameter(
                    "api_key",
                    apiKey
                )
            }
        return response.body<MoviesResponse>().results
    }
    suspend fun getPopular(): List<MovieDto> {
        return client.get(
            "$baseUrl/movie/popular"
        ){
            parameter(
                "api_key",
                apiKey
            )
        }.body<MoviesResponse>().results
    }
    suspend fun getTopRated(): List<MovieDto> {
        return client.get(
            "$baseUrl/movie/top_rated"
        ){
            parameter(
                "api_key",
                apiKey
            )
        }.body<MoviesResponse>().results
    }
    suspend fun getUpcoming(): List<MovieDto> {
        return client.get(
            "$baseUrl/movie/upcoming"
        ){
            parameter(
                "api_key",
                apiKey
            )
        }.body<MoviesResponse>().results
    }
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDto {
        return client.get(
            "$baseUrl/movie/$movieId"
        ) {
            parameter("api_key", apiKey)
        }.body()
    }
    suspend fun getMovieCredits(movieId: Int): CreditsDto {
        return client.get(
            "$baseUrl/movie/$movieId/credits"
        ) {
            parameter("api_key", apiKey)
        }.body()
    }
    suspend fun getSimilarMovies(movieId: Int): List<MovieDto> {
        return client.get(
            "$baseUrl/movie/$movieId/similar"
        ) {
            parameter("api_key", apiKey)
        }.body<MoviesResponse>().results
    }
}
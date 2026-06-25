package com.a2004256_ahmedmohamed.movieapp.presentation.search

import androidx.compose.runtime.mutableStateOf
import com.a2004256_ahmedmohamed.movieapp.data.remote.MovieApi
import com.a2004256_ahmedmohamed.movieapp.data.repository.MovieRepositoryImpl
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(
    private val api: MovieApi
) {
    var selectedFilter = mutableStateOf("Trending")
    val filters = listOf(
        "Trending",
        "Top Rated",
        "Popular",
        "Upcoming"
    )
    var query = mutableStateOf("")
    var movies = mutableStateOf<List<Movie>>(emptyList())
    var loading = mutableStateOf(false)
    private val repository = MovieRepositoryImpl(api)
    private var searchJob: Job? = null
    fun loadPopular() {
        searchJob?.cancel()

        loading.value = true

        searchJob = CoroutineScope(Dispatchers.Default).launch {
            try {
                val result = repository.getPopularMovies()
                movies.value = result
            } catch (e: Exception) {
                movies.value = emptyList()
            }

            loading.value = false
        }
    }

    fun loadTopRated() {
        searchJob?.cancel()

        loading.value = true

        searchJob = CoroutineScope(Dispatchers.Default).launch {
            try {
                val result = repository.getTopRatedMovies()
                movies.value = result
            } catch (e: Exception) {
                movies.value = emptyList()
            }

            loading.value = false
        }
    }

    fun loadUpcoming() {
        searchJob?.cancel()

        loading.value = true

        searchJob = CoroutineScope(Dispatchers.Default).launch {
            try {
                val result = repository.getUpcomingMovies()
                movies.value = result
            } catch (e: Exception) {
                movies.value = emptyList()
            }

            loading.value = false
        }
    }
    fun loadTrending() {
        searchJob?.cancel()

        loading.value = true

        searchJob = CoroutineScope(Dispatchers.Default).launch {
            try {
                val result = repository.getTrendingMovies()
                movies.value = result
            } catch (e: Exception) {
                movies.value = emptyList()
            }

            loading.value = false
        }
    }
    fun onQueryChange(text: String) {
        query.value = text

        searchJob?.cancel()

        if (text.length < 2) {
            movies.value = emptyList()
            return
        }

        searchJob = CoroutineScope(Dispatchers.Default).launch {

            delay(400)

            loading.value = true

            try {
                val result = repository.searchMovies(text)
                movies.value = result
            } catch (e: Exception) {
                movies.value = emptyList()
            }

            loading.value = false
        }
    }
}
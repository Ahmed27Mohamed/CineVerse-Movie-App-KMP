package com.a2004256_ahmedmohamed.movieapp.presentation.home

import com.a2004256_ahmedmohamed.movieapp.domain.model.Trailer
import com.a2004256_ahmedmohamed.movieapp.domain.repository.MovieRepository
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.MovieDetailsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) {

    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )

    private val _state = MutableStateFlow(
        HomeState()
    )

    val state = _state.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {

        scope.launch {

            try {

                _state.value =
                    _state.value.copy(
                        isLoading = true
                    )

                val trending =
                    repository.getTrendingMovies()

                val popular =
                    repository.getPopularMovies()

                val topRated =
                    repository.getTopRatedMovies()

                val upcoming =
                    repository.getUpcomingMovies()

                _state.value =
                    _state.value.copy(

                        trendingMovies =
                            trending,

                        popularMovies =
                            popular,

                        topRatedMovies =
                            topRated,

                        upcomingMovies =
                            upcoming,

                        isLoading = false
                    )

            } catch (e: Exception) {

                _state.value =
                    _state.value.copy(
                        isLoading = false,
                        error = e.message
                    )

            }

        }

    }
    private val _trailerState = MutableStateFlow<Trailer?>(null)
    val trailerState = _trailerState.asStateFlow()
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()
    fun loadTrailer(movieId: Int) {
        scope.launch {
            _loading.value = true

            try {
                _trailerState.value = repository.getTrailer(movieId)
            } catch (e: Exception) {
                _trailerState.value = null
            }

            _loading.value = false
        }
    }
    private val _detailsState =
        MutableStateFlow(MovieDetailsState())

    val detailsState =
        _detailsState.asStateFlow()


    fun loadMovieDetails(movieId: Int) {

        scope.launch {

            try {

                _detailsState.value =
                    _detailsState.value.copy(
                        isLoading = true
                    )

                val movie =
                    repository.getMovieDetails(movieId)

                _detailsState.value =
                    MovieDetailsState(
                        movie = movie,
                        isLoading = false
                    )

            } catch (e: Exception) {

                _detailsState.value =
                    MovieDetailsState(
                        error = e.message,
                        isLoading = false
                    )
            }

        }
    }
}
sealed class UiEvent {
    object NavigateBack : UiEvent()
}
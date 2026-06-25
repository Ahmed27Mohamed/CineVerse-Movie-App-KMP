package com.a2004256_ahmedmohamed.movieapp.presentation.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WatchlistViewModel(
    private val repository: WatchlistRepository
) : ViewModel() {

    private val _movies =
        MutableStateFlow<List<WatchlistEntity>>(emptyList())

    val movies: StateFlow<List<WatchlistEntity>>
            = _movies.asStateFlow()

    init {

        viewModelScope.launch {

            repository
                .getWatchlist()
                .collect {

                    _movies.value = it
                }
        }
    }

    fun toggle(movie: Movie) {

        viewModelScope.launch {

            val exists = _movies.value.any {
                it.id == movie.id
            }

            if (exists) {

                repository.removeMovie(movie.id)

            } else {

                repository.addMovie(
                    WatchlistEntity(
                        id = movie.id,
                        title = movie.title,
                        imageUrl = movie.imageUrl,
                        rating = movie.rating
                    )
                )
            }
        }
    }

    fun isSaved(movieId: Int): Boolean {

        return _movies.value.any {
            it.id == movieId
        }
    }

}
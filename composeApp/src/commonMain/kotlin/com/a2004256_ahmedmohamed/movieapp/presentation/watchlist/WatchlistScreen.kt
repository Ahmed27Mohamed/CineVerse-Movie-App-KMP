package com.a2004256_ahmedmohamed.movieapp.presentation.watchlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeScreenContent
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.MovieCard
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.MovieDetailsScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.TrailerScreen
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Background
import org.koin.compose.koinInject

class WatchlistScreen() : Screen {
    @Composable
    override fun Content() {
        WatchlistScreenContent()
    }
}


@Composable
fun WatchlistScreenContent() {
    val navigator = LocalNavigator.currentOrThrow
    val homeViewModel: HomeViewModel = koinInject()
    val vm: WatchlistViewModel = koinInject()
    val movies by vm.movies.collectAsState()

    if (movies.isEmpty()) {
        Text(
            text = "No movies saved yet",
            color = Color.White
        )
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(movies) { movie ->

            MovieCard(
                movie = Movie(
                    id = movie.id,
                    title = movie.title,
                    imageUrl = movie.imageUrl,
                    rating = movie.rating,
                    description = "",
                    runtime = 0,
                    ageRating = "",
                    cast = emptyList(),
                    similarMovies = emptyList()
                ),

                isSaved = vm.isSaved(movie.id),

                onSaveClick = {
                    vm.toggle(
                        Movie(
                            id = movie.id,
                            title = movie.title,
                            imageUrl = movie.imageUrl,
                            rating = movie.rating,
                            description = "",
                            runtime = 0,
                            ageRating = "",
                            cast = emptyList(),
                            similarMovies = emptyList()
                        )
                    )
                },

                onClick = {

                    navigator.push(
                        MovieDetailsScreen(
                            movieId = movie.id,
                            viewModel = homeViewModel,

                            onWatchTrailer = { selectedMovie ->

                                navigator.push(
                                    TrailerScreen(
                                        movieId = selectedMovie.id,
                                        viewModel = homeViewModel
                                    )
                                )
                            },

                            onWatchlistClick = {
                                vm.toggle(it)
                            }
                        )
                    )
                }
            )
        }
    }
}
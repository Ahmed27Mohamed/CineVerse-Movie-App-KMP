package com.a2004256_ahmedmohamed.movieapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.MovieDetailsScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.MovieSection
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.TrailerScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.watchlist.WatchlistViewModel
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Background
import org.koin.compose.koinInject

class HomeScreen() : Screen {
    @Composable
    override fun Content() {
        HomeScreenContent()
    }
}

@Composable
fun HomeScreenContent() {
    val viewModel: HomeViewModel = koinInject()
    val vm: WatchlistViewModel = koinInject()
    val navigator = LocalNavigator.currentOrThrow
    val state by viewModel.state.collectAsState()
    val movies by vm.movies.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        if (state.isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        item {
            val featuredMovie = remember(state.trendingMovies) {
                state.trendingMovies.randomOrNull()
            }
            featuredMovie?.let { movie ->
                FeaturedMovieBanner(
                    movie = movie,
                    onWatchClick = { movie ->
                        navigator.push(
                            TrailerScreen(
                                movieId = movie.id,
                                viewModel = viewModel
                            )
                        )
                    },
                    onMovieClick = { selectedMovie ->
                        navigator.push(
                            MovieDetailsScreen(
                                movieId = selectedMovie.id,
                                viewModel = viewModel,
                                onWatchTrailer = { movie ->
                                    navigator.push(
                                        TrailerScreen(
                                            movieId = movie.id,
                                            viewModel = viewModel
                                        )
                                    )
                                },
                                onWatchlistClick = {
                                    vm.toggle(it)
                                },
                            )
                        )
                    },
                    onWatchlistClick = {
                        vm.toggle(it)
                    },
                    isSaved = movies.any {
                        it.id == movie.id
                    },
                )
            }
        }
        item {
            MovieSection(
                title = "Trending",
                movies = state.trendingMovies,
                watchlistViewModel = vm,
                movieViewModel = movies,
                onMovieClick = {
                    navigator.push(
                        MovieDetailsScreen(
                            movieId = it.id,
                            viewModel = viewModel,
                            onWatchTrailer = { movie ->
                                navigator.push(
                                    TrailerScreen(
                                        movieId = movie.id,
                                        viewModel = viewModel
                                    )
                                )
                            },
                            onWatchlistClick = {
                                vm.toggle(it)
                            },
                        )
                    )
                }
            )
        }
        item {
            MovieSection(
                title = "Popular",
                movies = state.popularMovies,
                onMovieClick = {
                    navigator.push(
                        MovieDetailsScreen(
                            movieId = it.id,
                            viewModel = viewModel,
                            onWatchTrailer = { movie ->
                                navigator.push(
                                    TrailerScreen(
                                        movieId = movie.id,
                                        viewModel = viewModel
                                    )
                                )
                            },
                            onWatchlistClick = {
                                vm.toggle(it)
                            },
                        )
                    )
                },
                watchlistViewModel = vm,
                movieViewModel = movies,
            )
        }
        item {
            MovieSection(
                title = "Top Rated",
                movies = state.topRatedMovies,
                onMovieClick = {
                    navigator.push(
                        MovieDetailsScreen(
                            movieId = it.id,
                            viewModel = viewModel,
                            onWatchTrailer = { movie ->
                                navigator.push(
                                    TrailerScreen(
                                        movieId = movie.id,
                                        viewModel = viewModel
                                    )
                                )
                            },
                            onWatchlistClick = {
                                vm.toggle(it)
                            },
                        )
                    )
                },
                watchlistViewModel = vm,
                movieViewModel = movies,
            )
        }
        item {
            MovieSection(
                title = "Upcoming",
                movies = state.upcomingMovies,
                onMovieClick = {
                    navigator.push(
                        MovieDetailsScreen(
                            movieId = it.id,
                            viewModel = viewModel,
                            onWatchTrailer = { movie ->
                                navigator.push(
                                    TrailerScreen(
                                        movieId = movie.id,
                                        viewModel = viewModel
                                    )
                                )
                            },
                            onWatchlistClick = {
                                vm.toggle(it)
                            },
                        )
                    )
                },
                watchlistViewModel = vm,
                movieViewModel = movies,
            )
        }
        item {
            AIRecommendationSection()
        }
    }
}
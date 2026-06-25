package com.a2004256_ahmedmohamed.movieapp.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.a2004256_ahmedmohamed.movieapp.data.remote.MovieApi
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.MovieCard
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.MovieDetailsScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.TrailerScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.watchlist.WatchlistEntity
import com.a2004256_ahmedmohamed.movieapp.presentation.watchlist.WatchlistViewModel
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Background
import org.koin.compose.koinInject


class SearchScreen : Screen {
    @Composable
    override fun Content() {
        SearchScreenContent()
    }
}

@Composable
fun SearchScreenContent() {

    val vm: SearchViewModel = koinInject()
    val viewModel: HomeViewModel = koinInject()
    val vm2: WatchlistViewModel = koinInject()
    val movies2 by vm2.movies.collectAsState()
    val navigator = LocalNavigator.currentOrThrow
    val movies by vm.movies
    val loading by vm.loading
    val query by vm.query
    var showFilterDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        vm.loadTrending()
    }

    if (showFilterDialog) {

        AlertDialog(
            onDismissRequest = { showFilterDialog = false },
            title = {
                Text("Filter Movies")
            },
            text = {
                Column {

                    vm.filters.forEach { filter ->

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    vm.selectedFilter.value = filter
                                    showFilterDialog = false

                                    when (filter) {
                                        "Trending" -> vm.loadTrending()
                                        "Top Rated" -> vm.loadTopRated()
                                        "Popular" -> vm.loadPopular()
                                        "Upcoming" -> vm.loadUpcoming()
                                    }
                                }
                                .padding(12.dp)
                        ) {
                            Text(text = filter)
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showFilterDialog = false }) {
                    Text("Close")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
    ) {

        SearchBar(
            value = query,
            onValueChange = { vm.onQueryChange(it) },
            onFilterClick = { showFilterDialog = true },
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = if (query.isEmpty()) "Trending Movies" else "Search Results",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        if (loading) {

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(5) {
                    ShimmerCard()
                }
            }

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                items(movies) { movie ->

                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut()
                    ) {
                        MovieResultCard(
                            movie = movie,
                            movieViewModel = movies2,
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
                                            vm2.toggle(it)
                                        }
                                    ),
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onFilterClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text("Search movies, actors...", color = Color.Gray)
            },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1A1A22),
                unfocusedContainerColor = Color(0xFF1A1A22),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color(0xFF7C4DFF)
            )
        )

        Spacer(Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFF1A1A22))
                .clickable { onFilterClick() },
            contentAlignment = Alignment.Center
        ) {
            Text("⚙️")
        }
    }
}
@Composable
fun MovieResultCard(
    movie: Movie,
    movieViewModel: List<WatchlistEntity>,
    onMovieClick: (Movie) -> Unit,
) {
    val watchlistViewModel: WatchlistViewModel = koinInject()
    MovieCard(
        movie = movie,
        onClick = { onMovieClick(movie) },
        isSaved = movieViewModel.any {
            it.id == movie.id
        },
        onSaveClick = {
            watchlistViewModel.toggle(movie)
        },
    )
}
@Composable
fun ShimmerCard() {

    val transition = rememberInfiniteTransition()

    val alpha by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray.copy(alpha = alpha))
    )
}
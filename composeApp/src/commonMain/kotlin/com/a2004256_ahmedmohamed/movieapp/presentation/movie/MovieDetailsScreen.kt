package com.a2004256_ahmedmohamed.movieapp.presentation.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.a2004256_ahmedmohamed.movieapp.domain.model.Cast
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import com.a2004256_ahmedmohamed.movieapp.presentation.home.GradientWatchButton
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.watchlist.WatchlistViewModel
import org.koin.compose.koinInject

data class MovieDetailsScreen(
    val movieId: Int,
    val viewModel: HomeViewModel,
    val onWatchlistClick: (Movie) -> Unit,
    val onWatchTrailer: (Movie) -> Unit
) : Screen {
    @Composable
    override fun Content() {
        MovieDetailsScreenContent(
            movieId = movieId,
            viewModel = viewModel,
            onWatchTrailer = onWatchTrailer,
            onWatchlistClick = onWatchlistClick,
        )
    }
}

@Composable
fun MovieDetailsScreenContent(
    movieId: Int,
    viewModel: HomeViewModel,
    onWatchTrailer: (Movie) -> Unit,
    onWatchlistClick: (Movie) -> Unit
) {
    val navigator = LocalNavigator.currentOrThrow

    val state by
    viewModel.detailsState.collectAsState()

    val watchlistViewModel: WatchlistViewModel = koinInject()

    val watchlistMovies by watchlistViewModel.movies.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.loadMovieDetails(movieId)
    }

    if (state.isLoading) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        return
    }

    val movie = state.movie ?: return

    val isSaved = watchlistMovies.any {
        it.id == movie.id
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
            ) {

                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.4f),
                                    Color.Black
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {

                    Text(
                        text = movie.title,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Spacer(Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "${movie.runtime / 60}h ${movie.runtime % 60}m",
                            color = Color.LightGray
                        )

                        Spacer(Modifier.width(8.dp))

                        Text("•", color = Color.Gray)

                        Spacer(Modifier.width(8.dp))

                        Text(text = movie.ageRating, color = Color.LightGray)

                        Spacer(Modifier.width(12.dp))

                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.Yellow,
                            modifier = Modifier.size(18.dp)
                        )

                        Text(
                            text = " ${movie.rating}",
                            color = Color.White
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Row(
                        horizontalArrangement =
                            Arrangement.spacedBy(12.dp)
                    ) {

                        GradientWatchButton(
                            title = "Watch Trailer",
                            onWatchClick = onWatchTrailer,
                            movie = movie
                        )

                        IconButton(
                            onClick = {
                                watchlistViewModel.toggle(movie)
                            },
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(
                                        50
                                    )
                                )
                                .background(
                                    Color.White.copy(
                                        alpha = .2f
                                    )
                                )
                        ) {

                            androidx.compose.material3.Icon(
                                imageVector =
                                    if (isSaved)
                                        Icons.Filled.Bookmark
                                    else
                                        Icons.Outlined.BookmarkBorder,
                                contentDescription = null,
                                tint = Color.White
                            )

                        }
                    }
                }
            }
        }

        item {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    "Storyline",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = movie.description,
                    color = Color.Gray
                )

                Spacer(Modifier.height(20.dp))

                Text("Cast & Crew", color = Color.White)

                Spacer(Modifier.height(12.dp))

                LazyRow {
                    items(movie.cast.size) { index ->
                        CastItem(
                            cast = movie.cast[index]
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                Text("Similar Movies", color = Color.White)

                Spacer(Modifier.height(12.dp))

                LazyRow {
                    items(movie.similarMovies.size) { index ->
                        SimilarMovieCard(
                            movie = movie.similarMovies[index],
                            onClick = { selectedMovie ->
                                navigator.replace(
                                    MovieDetailsScreen(
                                        movieId = selectedMovie.id,
                                        viewModel = viewModel,
                                        onWatchTrailer = onWatchTrailer,
                                        onWatchlistClick = onWatchlistClick
                                    )
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
fun CastItem(
    cast: Cast
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 12.dp)
    ) {

        AsyncImage(
            model = cast.imageUrl,
            contentDescription = cast.name,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = CircleShape
                )
                .background(Color.DarkGray),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = cast.name,
            color = Color.White,
            maxLines = 1
        )

        Text(
            text = cast.character,
            color = Color.Gray,
            fontSize = 12.sp,
            maxLines = 1
        )
    }
}

@Composable
fun SimilarMovieCard(
    movie: Movie,
    onClick: (Movie) -> Unit
) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(end = 12.dp)
            .clickable {
                onClick(movie)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = movie.imageUrl,
            contentDescription = movie.title,
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .background(Color.DarkGray, RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = movie.title,
            color = Color.White,
            maxLines = 1
        )

        Text(
            text = "⭐ ${movie.rating}",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}
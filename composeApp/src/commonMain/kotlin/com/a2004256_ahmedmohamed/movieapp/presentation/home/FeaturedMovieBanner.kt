package com.a2004256_ahmedmohamed.movieapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Primary
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun FeaturedMovieBanner(
    movie: Movie,
    isSaved: Boolean,
    onWatchClick: (Movie) -> Unit,
    onWatchlistClick: (Movie) -> Unit,
    onMovieClick: (Movie) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(bottom = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Box {

            AsyncImage(
                model = movie.imageUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onMovieClick(movie)
                    }
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = .4f),
                                Color.Black
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(
                        Alignment.BottomStart
                    )
                    .padding(end = 20.dp, start = 20.dp, bottom = 30.dp)
            ) {

                Text(
                    text = "AI PICK OF THE WEEK",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 12.sp,
                    modifier = Modifier.background(
                        color = Primary,
                        shape = RoundedCornerShape(20.dp)
                    ).padding(horizontal = 6.dp, vertical = 3.dp)
                )

                Spacer(
                    modifier = Modifier.height(
                        8.dp
                    )
                )

                Text(
                    text = movie.title,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(
                        8.dp
                    )
                )

                Text(
                    text = movie.description,
                    color = Color.LightGray,
                    maxLines = 3,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(
                        16.dp
                    )
                )

                Row(
                    horizontalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    GradientWatchButton(
                        title = "Watch",
                        onWatchClick = onWatchClick,
                        movie = movie
                    )

                    IconButton(
                        onClick = {
                            onWatchlistClick(movie)
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

                        Icon(
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

}

@Composable
fun GradientWatchButton(title: String, onWatchClick: (Movie) -> Unit, movie: Movie) {
    val buttonShape = RoundedCornerShape(12.dp)
    Button(
        onClick = { onWatchClick(movie) },
        shape = buttonShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF8F86FF),
                        Color(0xFFF7A9B8)
                    )
                ),
                shape = buttonShape
            )
            .height(48.dp)
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}
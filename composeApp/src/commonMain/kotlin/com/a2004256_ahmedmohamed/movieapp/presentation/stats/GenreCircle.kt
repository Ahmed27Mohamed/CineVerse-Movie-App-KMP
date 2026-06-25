package com.a2004256_ahmedmohamed.movieapp.presentation.stats

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.MovieDetailsScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.MovieSection
import com.a2004256_ahmedmohamed.movieapp.presentation.movie.TrailerScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.watchlist.WatchlistViewModel
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Background
import org.koin.compose.koinInject

@Composable
fun GenreCircle() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor =
                    Color(0xFF0F1016)
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    "Favorite Genres",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )

                Spacer(
                    Modifier.height(20.dp)
                )

                Box(
                    modifier = Modifier
                        .size(220.dp)
                        .align(
                            Alignment.CenterHorizontally
                        ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Canvas(
                        modifier =
                            Modifier.fillMaxSize()
                    ) {

                        drawArc(
                            color =
                                Color(0xFFBEB8FF),
                            startAngle = -90f,
                            sweepAngle = 162f,
                            useCenter = false,
                            style = Stroke(
                                30f
                            )
                        )

                        drawArc(
                            color =
                                Color(0xFFFFA9C9),
                            startAngle = 72f,
                            sweepAngle = 90f,
                            useCenter = false,
                            style = Stroke(
                                30f
                            )
                        )

                        drawArc(
                            color =
                                Color(0xFFFFB97A),
                            startAngle = 162f,
                            sweepAngle = 72f,
                            useCenter = false,
                            style = Stroke(
                                30f
                            )
                        )
                    }

                    Column(
                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Text(
                            "45%",
                            color = Color.White,
                            fontWeight =
                                FontWeight.Bold,
                            fontSize = 34.sp
                        )

                        Text(
                            "Sci-Fi",
                            color =
                                Color.LightGray
                        )
                    }
                }

                Spacer(
                    Modifier.height(20.dp)
                )

                GenreRow(
                    Color(0xFFBEB8FF),
                    "Sci-Fi & Fantasy",
                    "45%"
                )

                GenreRow(
                    Color(0xFFFFA9C9),
                    "Drama",
                    "25%"
                )

                GenreRow(
                    Color(0xFFFFB97A),
                    "Action",
                    "20%"
                )

                GenreRow(
                    Color.DarkGray,
                    "Other",
                    "10%"
                )
            }
        }
    }
}

@Composable
fun GenreRow(
    color: Color,
    title: String,
    percent: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        Row {

            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(color)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                title,
                color = Color.White
            )
        }

        Text(
            percent,
            color = Color.White
        )
    }
}
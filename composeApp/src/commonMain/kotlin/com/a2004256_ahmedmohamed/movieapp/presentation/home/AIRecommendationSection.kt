package com.a2004256_ahmedmohamed.movieapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie

@Composable
fun AIRecommendationSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {

        Text(
            text = "AI Recommendations",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {

                AIRecommendationCard(
                    title = "Mind Blowing",
                    subtitle = "Movies with unexpected endings",
                    emoji = "🧠"
                )

            }

            item {

                AIRecommendationCard(
                    title = "Funny",
                    subtitle = "Movies to make you laugh",
                    emoji = "😂"
                )

            }

            item {

                AIRecommendationCard(
                    title = "Emotional",
                    subtitle = "Movies that hit deeply",
                    emoji = "❤️"
                )

            }

        }

    }

}

@Composable
fun AIRecommendationCard(
    title: String,
    subtitle: String,
    emoji: String
) {

    Card(
        modifier = Modifier
            .width(220.dp)
            .height(120.dp),

        shape = RoundedCornerShape(24.dp),

        ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF9B87F5),
                            Color(0xFF5B4BFF)
                        )
                    )
                )
                .padding(16.dp)
        ) {

            Column {

                Text(
                    text = emoji,
                    fontSize = 28.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = subtitle,
                    color = Color.White.copy(alpha=.7f),
                    fontSize = 12.sp
                )

            }

        }

    }

}
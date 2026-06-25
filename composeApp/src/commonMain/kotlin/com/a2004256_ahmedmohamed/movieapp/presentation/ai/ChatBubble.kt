package com.a2004256_ahmedmohamed.movieapp.presentation.ai

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SearchBarDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie

@Composable
fun ChatBubble(message: ChatMessage) {

    val isUser = message.isUser

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
    ) {

        Box(
            modifier = Modifier
                .widthIn(max = 300.dp)
                .border(
                    width = 2.dp,
                    brush = if (isUser) {
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF8F86FF),
                                Color(0xFFF7A9B8)
                            )
                        )
                    } else {
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF1A1A22),
                                Color(0xFF1A1A22)
                            )
                        )
                    },
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(12.dp)
        ) {
            Text(
                text = message.text,
                color = Color.White
            )
        }

        message.movie?.let {
            Spacer(Modifier.height(8.dp))
            MovieSuggestionCard(it)
        }
    }
}
@Composable
fun MovieSuggestionCard(movie: com.a2004256_ahmedmohamed.movieapp.presentation.ai.Movie) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF14141C)
        )
    ) {

        Column {

            AsyncImage(
                model = movie.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Column(Modifier.padding(12.dp)) {

                Text(movie.title, color = Color.White, fontSize = 18.sp)

                Text(
                    movie.description,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    maxLines = 2
                )

                Spacer(Modifier.height(8.dp))

                Row {
                    Chip(movie.genre)
                    Spacer(Modifier.width(8.dp))
                    Chip("${movie.rating} IMDb")
                }
            }
        }
    }
}
@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier
            .background(
                Color(0xFF2A2A35),
                RoundedCornerShape(50)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(text, color = Color.White, fontSize = 12.sp)
    }
}
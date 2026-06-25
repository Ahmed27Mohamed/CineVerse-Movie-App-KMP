package com.a2004256_ahmedmohamed.movieapp.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie

@Composable
fun SmallMovieCard(movie: Movie) {

    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(8.dp)
    ) {

        AsyncImage(
            model = movie.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .height(140.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Text(
            movie.title,
            color = Color.White,
            maxLines = 1
        )
    }
}
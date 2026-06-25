package com.a2004256_ahmedmohamed.movieapp.presentation.ai

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuickActions(onClick: (String) -> Unit) {

    val actions = listOf(
        "Recommend sci-fi movie",
        "What's trending?",
        "Best comedy movie"
    )

    LazyRow(
        contentPadding = PaddingValues(12.dp)
    ) {
        items(actions) { item ->
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .background(
                        Color(0xFF1C1C25),
                        RoundedCornerShape(50)
                    )
                    .padding(horizontal = 14.dp, vertical = 8.dp)
                    .clickable { onClick(item) }
            ) {
                Text(item, color = Color.White, fontSize = 12.sp)
            }
        }
    }
}
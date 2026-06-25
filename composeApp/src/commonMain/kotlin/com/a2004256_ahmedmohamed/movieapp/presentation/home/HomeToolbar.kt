package com.a2004256_ahmedmohamed.movieapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import com.a2004256_ahmedmohamed.movieapp.domain.model.Movie
import com.a2004256_ahmedmohamed.movieapp.presentation.profile.UserProfile
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Background

@Composable
fun HomeToolbar(
    title: String = "CineVerse",
    user: UserProfile,
    onSearchClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .drawBehind {
                drawLine(
                    color = Color.DarkGray.copy(alpha = 3f),
                    start = Offset(
                        0f,
                        size.height
                    ),
                    end = Offset(
                        size.width,
                        size.height
                    ),
                    strokeWidth = 2f
                )
            }
            .padding(end = 18.dp, start = 18.dp, bottom = 18.dp, top = 38.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF8F86FF),
                        Color(0xFFF7A9B8)
                    )
                )
            )
        )

        Row(verticalAlignment = Alignment.CenterVertically) {

            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }

            IconButton(onClick = onNotificationClick) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = onProfileClick,
            ) {
                AsyncImage(
                    model = user.image,
                    contentDescription = null,
                    modifier = Modifier.size(34.dp).border(4.dp, Color(0xC7C1FF)).clip(CircleShape)
                )
            }
        }
    }
}
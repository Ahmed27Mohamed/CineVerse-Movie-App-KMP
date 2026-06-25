package com.a2004256_ahmedmohamed.movieapp.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a2004256_ahmedmohamed.movieapp.presentation.stats.Stats

@Composable
fun ProfileStatsRow(
    stats: Stats,
    followers: Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        StatBox("Movies Watched", stats.totalWatched.toString(), Icons.Default.DateRange)

        StatBox("Days Watched", "${stats.watchTimeMinutes / 1440}", Icons.Default.AccessTime)

        StatBox("Followers", followers.toString(), Icons.Default.Person)
    }
}
@Composable
fun StatBox(title: String, value: String, icon: ImageVector) {

    Card(
        modifier = Modifier.width(100.dp),
        colors = CardDefaults.cardColors(Color(0xFF1A1A1A))
    ) {

        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(icon, null, tint = Color.White)

            Spacer(Modifier.height(6.dp))

            Text(value, color = Color.White, fontWeight = FontWeight.Bold)

            Text(title, color = Color.Gray, fontSize = 8.sp, maxLines = 1)
        }
    }
}
package com.a2004256_ahmedmohamed.movieapp.presentation.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeScreenContent
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Background
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.database.DatabaseReference
import dev.gitlive.firebase.database.ServerValue
import kotlinx.coroutines.flow.first
import org.koin.compose.koinInject

class StatsScreen() : Screen {
    @Composable
    override fun Content() {
        StatsScreenContent()
    }
}

@Composable
fun StatsScreenContent() {

    val vm: StatsViewModel = koinInject()
    val stats by vm.stats.collectAsState()
    val achievements by vm.achievements.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .background(Background),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            StatsCard(
                icon = Icons.Default.DateRange,
                title = "Total Watched",
                value = stats.totalWatched.toString(),
                subTitle = "+ this month"
            )
        }

        item {
            StatsCard(
                icon = Icons.Default.AccessTime,
                title = "Watch Time",
                value = "${stats.watchTimeMinutes} min",
                subTitle = "Total time"
            )
        }

        item {
            StatsCard(
                icon = Icons.Default.EmojiEvents,
                title = "Cine Rank",
                value = stats.cineRank,
                subTitle = "Based on activity"
            )
        }

        item {
            WeeklyChart(values = stats.weekly)
        }

        item {
            Text(
                "Achievements",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }

        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(350.dp)
            ) {
                items(achievements) { item ->
                    AchievementCard(item)
                }
            }
        }
    }
}
suspend fun addWatchHistory(
    auth: FirebaseAuth,
    db: DatabaseReference,
    movieId: Int,
    watchTimeMinutes: Int
) {
    val uid = auth.currentUser?.uid ?: return

    val userRef = db.child("users").child(uid)

    userRef.child("watchHistory")
        .child(movieId.toString())
        .setValue(true)

    val stats = userRef.child("stats").valueEvents.first()

    val total = stats.child("totalWatched").value?.toString()?.toIntOrNull() ?: 0
    val time = stats.child("watchTimeMinutes").value?.toString()?.toIntOrNull() ?: 0

    userRef.child("watchHistory")
        .child(movieId.toString())
        .setValue(true)

    userRef.child("stats").child("totalWatched")
        .setValue(total + 1)

    userRef.child("stats").child("watchTimeMinutes")
        .setValue(time + watchTimeMinutes)
}
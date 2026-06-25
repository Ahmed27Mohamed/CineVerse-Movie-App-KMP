package com.a2004256_ahmedmohamed.movieapp.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeScreenContent
import com.a2004256_ahmedmohamed.movieapp.presentation.stats.AchievementCard
import com.a2004256_ahmedmohamed.movieapp.presentation.stats.StatsViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.watchlist.WatchlistViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.watchlist.toMovieFixed
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Background
import org.koin.compose.koinInject

class ProfileScreen() : Screen {
    @Composable
    override fun Content() {
        ProfileScreenContent()
    }
}

@Composable
fun ProfileScreenContent() {
    val navigator = LocalNavigator.currentOrThrow
    val statsVm: StatsViewModel = koinInject()
    val watchVm: WatchlistViewModel = koinInject()
    val stats by statsVm.stats.collectAsState()
    val achievements by statsVm.achievements.collectAsState()
    val watchlist by watchVm.movies.collectAsState()
    val vm: ProfileViewModel = koinInject()
    val user by vm.user.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {

        item {
            ProfileHeader(
                user = user,
                rank = stats.cineRank,
                onEditClick = {
                    navigator.push(
                        EditProfileScreen(currentUser = user)
                    )
                }
            )
        }

        item {
            ProfileStatsRow(
                stats = stats,
                followers = 1250
            )
        }

        item {
            ProfileMenuGrid()
        }

        item {
            SectionTitle("Watchlist")

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(watchlist.take(10)) { entity ->

                    SmallMovieCard(
                        movie = entity.toMovieFixed()
                    )
                }
            }
        }

        item {
            SectionTitle("Recent Achievements")

            LazyRow {
                items(achievements.take(5)) {
                    AchievementCard(it)
                }
            }
        }

        item {
            SectionTitle("Favorite Genres")

            Column(Modifier.padding(16.dp)) {
                stats.genres.forEach { (name, percent) ->
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(name, color = Color.White)
                        Text("${(percent * 100).toInt()}%", color = Color.Gray)
                    }
                }
            }
        }
    }
}
@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}
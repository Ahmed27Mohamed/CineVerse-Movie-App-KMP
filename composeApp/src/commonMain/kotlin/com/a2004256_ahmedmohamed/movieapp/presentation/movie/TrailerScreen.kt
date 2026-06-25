package com.a2004256_ahmedmohamed.movieapp.presentation.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.stats.addWatchHistory
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.database.DatabaseReference
import org.koin.compose.koinInject

class TrailerScreen(private val movieId: Int, private val viewModel: HomeViewModel) : Screen {
    @Composable
    override fun Content() {
        val trailer by viewModel.trailerState.collectAsState()
        val loading by viewModel.loading.collectAsState()
        val auth: FirebaseAuth = koinInject()
        val db: DatabaseReference = koinInject()

        LaunchedEffect(movieId) {
            viewModel.loadTrailer(movieId)
            addWatchHistory(auth, db, movieId, watchTimeMinutes = 10)
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            when {
                loading -> {
                    CircularProgressIndicator()
                }

                trailer != null -> {
                    YouTubePlayer(
                        videoKey = trailer!!.key,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                else -> {
                    Text("No trailer found")
                }
            }
        }
    }
}

@Composable
expect fun YouTubePlayer(videoKey: String, modifier: Modifier = Modifier)
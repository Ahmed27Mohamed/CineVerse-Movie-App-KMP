package com.a2004256_ahmedmohamed.movieapp.presentation.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatsViewModel(
    private val auth: FirebaseAuth,
    private val db: DatabaseReference
) : ViewModel() {

    private val _stats = MutableStateFlow(Stats())
    val stats = _stats.asStateFlow()

    private val _achievements = MutableStateFlow<List<Achievement>>(emptyList())
    val achievements = _achievements.asStateFlow()

    init {
        observeStats()
        observeAchievements()
    }

    private fun observeStats() {
        val uid = auth.currentUser?.uid ?: return

        viewModelScope.launch {

            db.child("users")
                .child(uid)
                .child("stats")
                .valueEvents
                .collect { snapshot ->

                    val total = snapshot.child("totalWatched")
                        .value?.toString()?.toIntOrNull() ?: 0

                    val time = snapshot.child("watchTimeMinutes")
                        .value?.toString()?.toIntOrNull() ?: 0

                    val rank = when {
                        total > 200 -> "Master"
                        total > 100 -> "Expert"
                        total > 50 -> "Pro"
                        else -> "Rookie"
                    }

                    _stats.value = Stats(
                        totalWatched = total,
                        watchTimeMinutes = time,
                        cineRank = rank,
                        weekly = generateFakeWeek(time)
                    )
                }
        }
    }

    private fun observeAchievements() {
        viewModelScope.launch {

            db.child("achievements")
                .valueEvents
                .collect { snapshot ->

                    val list = snapshot.children.mapNotNull {
                        it.value<Achievement>()
                    }

                    _achievements.value = list
                }
        }
    }

    private fun generateFakeWeek(total: Int): List<Float> {
        return listOf(
            total * 0.1f,
            total * 0.15f,
            total * 0.2f,
            total * 0.1f,
            total * 0.2f,
            total * 0.15f,
            total * 0.1f
        )
    }
}
package com.a2004256_ahmedmohamed.movieapp.presentation.watchlist

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WatchlistRepository {

    private val auth = Firebase.auth
    private val db = Firebase.database.reference()

    suspend fun addMovie(movie: WatchlistEntity) {

        val uid = auth.currentUser?.uid ?: return

        db.child("users")
            .child(uid)
            .child("watchlist")
            .child(movie.id.toString())
            .setValue(movie)
    }

    suspend fun removeMovie(movieId: Int) {

        val uid = auth.currentUser?.uid ?: return

        db.child("users")
            .child(uid)
            .child("watchlist")
            .child(movieId.toString())
            .removeValue()
    }

    fun getWatchlist(): Flow<List<WatchlistEntity>> {

        val uid = auth.currentUser?.uid ?: return kotlinx.coroutines.flow.flowOf(emptyList())

        return db
            .child("users")
            .child(uid)
            .child("watchlist")
            .valueEvents
            .map { snapshot ->

                snapshot.children.mapNotNull {

                    try {
                        it.value<WatchlistEntity>()
                    } catch (_: Exception) {
                        null
                    }
                }
            }
    }
}
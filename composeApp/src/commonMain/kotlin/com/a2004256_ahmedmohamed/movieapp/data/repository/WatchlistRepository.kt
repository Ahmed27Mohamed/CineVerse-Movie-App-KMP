package com.a2004256_ahmedmohamed.movieapp.data.repository

import com.a2004256_ahmedmohamed.movieapp.presentation.watchlist.WatchlistEntity
import dev.gitlive.firebase.database.FirebaseDatabase

class WatchlistRepository(
    private val database: FirebaseDatabase
) {

    private fun watchlistRef(uid: String) =
        database.reference()
            .child("users")
            .child(uid)
            .child("watchlist")

    suspend fun addMovie(
        uid: String,
        movie: WatchlistEntity
    ) {
        watchlistRef(uid)
            .child(movie.id.toString())
            .setValue(movie)
    }

    suspend fun removeMovie(
        uid: String,
        movieId: Int
    ) {
        watchlistRef(uid)
            .child(movieId.toString())
            .removeValue()
    }
}
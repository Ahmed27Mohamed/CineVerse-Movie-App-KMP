package com.a2004256_ahmedmohamed.movieapp.presentation.login_and_register

import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser

class FirebaseAuthService(
    private val auth: FirebaseAuth
) {

    suspend fun login(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun register(email: String, password: String, name: String): Boolean {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password)

            result.user?.let {
                it.updateProfile(
                    displayName = name
                )
            }

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun currentUser(): FirebaseUser? {
        return auth.currentUser
    }

    suspend fun logout() {
        auth.signOut()
    }
}
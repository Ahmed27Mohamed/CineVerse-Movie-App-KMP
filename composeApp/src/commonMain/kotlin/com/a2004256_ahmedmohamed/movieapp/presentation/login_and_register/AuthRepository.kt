package com.a2004256_ahmedmohamed.movieapp.presentation.login_and_register

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun register(email: String, password: String, name: String): Boolean
}
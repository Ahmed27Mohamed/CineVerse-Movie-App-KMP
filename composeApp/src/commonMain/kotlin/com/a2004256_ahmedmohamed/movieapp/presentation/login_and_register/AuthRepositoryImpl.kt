package com.a2004256_ahmedmohamed.movieapp.presentation.login_and_register

class AuthRepositoryImpl(
    private val service: FirebaseAuthService
) : AuthRepository {

    override suspend fun login(email: String, password: String): Boolean {
        return service.login(email, password)
    }

    override suspend fun register(email: String, password: String, name: String): Boolean {
        return service.register(email, password, name)
    }
}
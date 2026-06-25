package com.a2004256_ahmedmohamed.movieapp.presentation.ai

class AIRepository(
    private val client: OpenAIClient
) {

    suspend fun chat(message: String): String {
        return client.sendMessage(message)
    }
}
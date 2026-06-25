package com.a2004256_ahmedmohamed.movieapp.presentation.ai

import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.Serializable

@Serializable
data class OpenAiRequest(
    val model: String,
    val messages: List<OpenAiMessage>
)

@Serializable
data class OpenAiMessage(
    val role: String,
    val content: String
)

class OpenAIClient(
    private val httpClient: HttpClient
) {
    val API_KEY = "API_KEY"

    suspend fun sendMessage(message: String): String {

        val response = httpClient.post("https://api.openai.com/v1/chat/completions") {

            header("Authorization", "Bearer $API_KEY")
            header("Content-Type", "application/json")

            setBody(
                OpenAiRequest(
                    model = "gpt-4o-mini",
                    messages = listOf(
                        OpenAiMessage(role = "user", content = message)
                    )
                )
            )
        }

        val json = response.bodyAsText()

        return extractReply(json)
    }

    private fun extractReply(json: String): String {
        return Regex("\"content\":\"(.*?)\"")
            .find(json)
            ?.groupValues?.get(1)
            ?.replace("\\n", "\n")
            ?: "No response"
    }
}
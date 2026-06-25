package com.a2004256_ahmedmohamed.movieapp.presentation.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AIViewModel(
    private val repo: AIRepository
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()

    fun sendMessage(text: String) {

        _messages.value = _messages.value + ChatMessage(text, true)

        viewModelScope.launch {

            val reply = repo.chat(text)

            _messages.value = _messages.value + ChatMessage(
                text = reply,
                isUser = false
            )
        }
    }
}
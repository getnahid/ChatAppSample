package com.coolncoolapps.chatappsample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coolncoolapps.chatappsample.data.api.ChatMessage
import com.coolncoolapps.chatappsample.domain.repository.ChatRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import androidx.paging.cachedIn

class ChatRoomViewModel(
    private val repository: ChatRepository,
    private val userId: String
) : ViewModel() {

    val messagesFlow = repository.getPaginatedMessages(userId)
        .flow
        .cachedIn(viewModelScope)

    private val _sentMessages = MutableSharedFlow<ChatMessage>()
    val sentMessages: SharedFlow<ChatMessage> = _sentMessages

    fun sendMessage(content: String) {
        val message = ChatMessage(
            senderId = "me",
            receiverId = userId,
            content = content,
            timestamp = System.currentTimeMillis()
        )
        viewModelScope.launch {
            repository.sendMessage(message)
            _sentMessages.emit(message)
        }
    }
}

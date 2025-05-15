package com.coolncoolapps.chatappsample.domain.repository

import androidx.paging.Pager
import com.coolncoolapps.chatappsample.data.api.ChatMessage

interface ChatRepository {
    fun getPaginatedMessages(userId: String): Pager<Long, ChatMessage>
    fun sendMessage(message: ChatMessage)
}

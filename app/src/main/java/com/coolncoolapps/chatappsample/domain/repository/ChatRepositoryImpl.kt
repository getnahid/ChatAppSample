package com.coolncoolapps.chatappsample.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.coolncoolapps.chatappsample.data.api.ChatApiService
import com.coolncoolapps.chatappsample.data.api.ChatMessage
import com.coolncoolapps.chatappsample.data.db.ChatMessageDao
import com.coolncoolapps.chatappsample.data.db.ChatMessageEntity
import com.coolncoolapps.chatappsample.data.paging.ChatPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatRepositoryImpl(
    private val api: ChatApiService,
    private val dao: ChatMessageDao
) : ChatRepository {

    override fun getPaginatedMessages(userId: String): Pager<Long, ChatMessage> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { ChatPagingSource(api, userId) }
        )
    }

    override fun sendMessage(message: ChatMessage) {
        // Optionally post to server via socket, here we cache in Room
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertAll(
                listOf(
                    ChatMessageEntity(
                        senderId = message.senderId,
                        receiverId = message.receiverId,
                        content = message.content,
                        timestamp = message.timestamp
                    )
                )
            )
        }
    }
}

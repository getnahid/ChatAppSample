package com.coolncoolapps.chatappsample.data.api

data class ChatMessage(
    val senderId: String,
    val receiverId: String,
    val content: String,
    val timestamp: Long
)

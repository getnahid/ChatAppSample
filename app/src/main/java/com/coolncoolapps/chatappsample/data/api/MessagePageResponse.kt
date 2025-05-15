package com.coolncoolapps.chatappsample.data.api

data class MessagePageResponse(
    val messages: List<ChatMessage>,
    val nextCursor: Long?
)

package com.coolncoolapps.chatappsample.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class ChatMessageEntity(
    @PrimaryKey val timestamp: Long,
    val senderId: String,
    val receiverId: String,
    val content: String
)

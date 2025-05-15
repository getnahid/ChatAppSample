package com.coolncoolapps.chatappsample.data.db

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface ChatMessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(messages: List<ChatMessageEntity>)

    @Query("""
        SELECT * FROM messages 
        WHERE senderId = :userId OR receiverId = :userId 
        ORDER BY timestamp DESC
    """)
    fun getMessages(userId: String): PagingSource<Int, ChatMessageEntity>
}

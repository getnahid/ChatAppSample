package com.coolncoolapps.chatappsample.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.coolncoolapps.chatappsample.data.api.ChatApiService
import com.coolncoolapps.chatappsample.data.api.ChatMessage

class ChatPagingSource(
    private val api: ChatApiService,
    private val userId: String
) : PagingSource<Long, ChatMessage>() {

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, ChatMessage> {
        return try {
            val before = params.key
            val response = api.getMessages(userId, before)
            LoadResult.Page(
                data = response.messages,
                prevKey = null,
                nextKey = response.nextCursor
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Long, ChatMessage>): Long? = null
}

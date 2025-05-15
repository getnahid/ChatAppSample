package com.coolncoolapps.chatappsample.data.api

interface ChatApiService {
    @GET("chat/messages/{userId}")
    suspend fun getMessages(
        @Path("userId") userId: String,
        @Query("before") before: Long? = null,
        @Query("limit") limit: Int = 20
    ): MessagePageResponse
}

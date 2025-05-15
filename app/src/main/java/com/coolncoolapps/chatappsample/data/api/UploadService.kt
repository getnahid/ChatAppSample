package com.coolncoolapps.chatappsample.data.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface UploadService {

    @Multipart
    @POST("chat/upload")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): Response<String>

    @GET
    @Streaming
    suspend fun downloadImage(@Url fileUrl: String): Response<ResponseBody>
}

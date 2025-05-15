package com.coolncoolapps.chatappsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.coolncoolapps.chatappsample.data.api.ChatApiService
import com.coolncoolapps.chatappsample.domain.repository.ChatRepositoryImpl
import com.coolncoolapps.chatappsample.presentation.ui.ChatAppNavHost
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            ChatDatabase::class.java, "chat-db"
        ).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://your.api.server/") // replace with your server
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ChatApiService::class.java)
        val repository = ChatRepositoryImpl(api, db.chatDao())

        setContent {
            ChatAppNavHost(repository)
        }
    }
}

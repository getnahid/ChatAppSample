package com.coolncoolapps.chatappsample.data.api

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.*

class ChatWebSocketClient(
    private val userId: String,
    private val serverUrl: String
) {
    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null
    private val _incomingMessages = MutableSharedFlow<ChatMessage>()
    val incomingMessages: SharedFlow<ChatMessage> = _incomingMessages

    fun connect() {
        val request = Request.Builder().url("$serverUrl/socket?userId=$userId").build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                println("WebSocket Connected")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                try {
                    val message = Json.decodeFromString<ChatMessage>(text)
                    _incomingMessages.tryEmit(message)
                } catch (e: Exception) {
                    println("Invalid JSON: $text")
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                println("WebSocket Error: ${t.message}")
            }
        })
    }

    fun disconnect() {
        webSocket?.close(1000, "Closing")
    }

    fun sendMessage(message: ChatMessage) {
        val json = Json.encodeToString(message)
        webSocket?.send(json)
    }
}

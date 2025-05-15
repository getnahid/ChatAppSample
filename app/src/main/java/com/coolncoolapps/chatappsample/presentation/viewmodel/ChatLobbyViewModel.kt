package com.coolncoolapps.chatappsample.presentation.viewmodel

import androidx.lifecycle.ViewModel

class ChatLobbyViewModel : ViewModel() {
    val users = listOf(
        ChatUser("1", "Alice"),
        ChatUser("2", "Bob"),
        ChatUser("3", "Charlie")
    )
}

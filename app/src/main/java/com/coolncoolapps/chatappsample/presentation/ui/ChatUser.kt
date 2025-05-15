package com.coolncoolapps.chatappsample.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coolncoolapps.chatappsample.presentation.viewmodel.ChatUser

@Composable
fun ChatLobbyScreen(users: List<ChatUser>, onUserClick: (ChatUser) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Chat Lobby") })

        LazyColumn {
            items(users.size) { index ->
                val user = users[index]
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onUserClick(user) }
                        .padding(16.dp)
                ) {
                    Text(user.name)
                }
                Divider()
            }
        }
    }
}

package com.coolncoolapps.chatappsample.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.LazyPagingItems
import com.coolncoolapps.chatappsample.presentation.viewmodel.ChatRoomViewModel

@Composable
fun ChatRoomScreen(viewModel: ChatRoomViewModel, onBack: () -> Unit) {
    val messages = viewModel.messagesFlow.collectAsLazyPagingItems()
    var inputText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Chat Room") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        LazyColumn(
            reverseLayout = true,
            modifier = Modifier.weight(1f).padding(8.dp)
        ) {
            items(messages.itemCount) { index ->
                messages[index]?.let {
                    Text(text = "${it.senderId}: ${it.content}", modifier = Modifier.padding(4.dp))
                }
            }

            messages.apply {
                when {
                    loadState.append is androidx.paging.LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        }
                    }
                    loadState.append is androidx.paging.LoadState.Error -> {
                        item {
                            Text("Failed to load more messages")
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type your message") }
            )
            Button(onClick = {
                viewModel.sendMessage(inputText)
                inputText = ""
            }, modifier = Modifier.padding(start = 8.dp)) {
                Text("Send")
            }
        }
    }
}

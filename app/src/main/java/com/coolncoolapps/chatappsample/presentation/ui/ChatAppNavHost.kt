package com.coolncoolapps.chatappsample.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.coolncoolapps.chatappsample.domain.repository.ChatRepository
import com.coolncoolapps.chatappsample.presentation.viewmodel.ChatLobbyViewModel
import com.coolncoolapps.chatappsample.presentation.viewmodel.ChatRoomViewModel
import androidx.navigation.compose.rememberNavController

@Composable
fun ChatAppNavHost(repository: ChatRepository) {
    val navController = rememberNavController()
    val lobbyViewModel = ChatLobbyViewModel()

    NavHost(navController = navController, startDestination = "lobby") {
        composable("lobby") {
            ChatLobbyScreen(users = lobbyViewModel.users) { user ->
                navController.navigate("chatroom/${user.id}/${user.name}")
            }
        }
        composable("chatroom/{userId}/{userName}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val viewModel = ChatRoomViewModel(repository, userId)
            ChatRoomScreen(viewModel) {
                navController.popBackStack()
            }
        }
    }
}

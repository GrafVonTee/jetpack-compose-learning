package com.example.composecourse.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecourse.graphmenu.GraphScreen
import com.example.composecourse.historymenu.HistoryScreen
import com.example.composecourse.homemenu.HomeScreen
import com.example.composecourse.roomtable.RecordState
import com.example.composecourse.roomtable.RecordEvent

@Composable
fun NavigationScreen(
    state: RecordState,
    onEvent: (RecordEvent) -> Unit,
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, bottomBarItems)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(RecordEvent.DeleteLast) },
            ) {
                Icon(Icons.Filled.Refresh, contentDescription = "Revert Last")
            }
        },
        content = { padding ->
            padding

            NavHost(
                navController = navController,
                startDestination = Screen.HomeScreen.route
            ) {
                composable(route = Screen.HomeScreen.route) {
                    HomeScreen(state, onEvent)
                }
                composable(route = Screen.GraphScreen.route) {
                    GraphScreen(state, onEvent)
                }
                composable(route = Screen.HistoryScreen.route) {
                    HistoryScreen(state, onEvent)
                }
            }
        }
    )
}
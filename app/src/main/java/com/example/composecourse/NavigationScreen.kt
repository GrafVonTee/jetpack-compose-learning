package com.example.composecourse

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecourse.graphmenu.GraphScreen
import com.example.composecourse.historymenu.HistoryScreen
import com.example.composecourse.homemenu.HomeScreen

@Composable
fun NavigationScreen(viewModel: PeriodDataViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, bottomBarItems)
        }
    ) { item ->
        item

        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(viewModel)
            }
            composable(route = Screen.GraphScreen.route) {
                GraphScreen(viewModel)
            }
            composable(route = Screen.HistoryScreen.route) {
                HistoryScreen(viewModel)
            }
        }
    }
}
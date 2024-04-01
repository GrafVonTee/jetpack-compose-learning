package com.example.composecourse.navigation

sealed class Screen(var route: String) {
    object HomeScreen : Screen("main_screen")
    object GraphScreen : Screen("graph_screen")
    object HistoryScreen : Screen("history_screen")
}
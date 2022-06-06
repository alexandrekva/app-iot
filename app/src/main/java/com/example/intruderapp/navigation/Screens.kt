package com.example.intruderapp.navigation

sealed class Screens(val route: String) {
    object Login : Screens(route = "login_screen")
    object Sync : Screens(route = "sync_screen")
    object Alarm : Screens(route = "alarm_screen")
}
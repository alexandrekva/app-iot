package com.example.intruderapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.intruderapp.ui.login.LoginScreen
import com.example.intruderapp.ui.sync.SyncScreen

@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screens.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screens.Sync.route) {
            SyncScreen(navController = navController)
        }
    }
}
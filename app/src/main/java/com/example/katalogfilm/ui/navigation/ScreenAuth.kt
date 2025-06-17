package com.example.katalogfilm.ui.navigation

sealed class AuthScreen(val route: String) {
    object Splash : AuthScreen("splash")
    object Login : AuthScreen("login")
    object Register : AuthScreen("register")
}

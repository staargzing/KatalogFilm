package com.example.katalogfilm.ui.main

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.katalogfilm.ui.navigation.AppNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val hideBars = currentRoute in listOf("splash", "login", "register", "detail/{movieId}")


    Scaffold(
        topBar = {
            if (!hideBars) {
                TopAppBar(
                    title = {
                        Text(
                            text = when (currentRoute) {
                                "home" -> "Film Populer"
                                "favorite" -> "Favorit"
                                "profile" -> "Profil"
                                else -> ""
                            }
                        )
                    }
                )
            }
        },
        bottomBar = {
            if (!hideBars) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        AppNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


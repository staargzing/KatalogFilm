package com.example.katalogfilm.ui.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.katalogfilm.datastore.SessionManager
import kotlinx.coroutines.delay
import com.example.katalogfilm.ui.navigation.Screen

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val loginState by SessionManager.getLoginState(context).collectAsState(initial = false to null)

    LaunchedEffect(loginState.first) {
        delay(1500)
        if (loginState.first) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        } else {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Katalog Film", style = MaterialTheme.typography.headlineMedium)
    }
}


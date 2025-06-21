package com.example.katalogfilm.ui.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.katalogfilm.datastore.SessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        delay(1500) // 1.5 detik splash

        val userId = SessionManager.getUserId(context)
        if (userId != null) {
            // User sudah login
            navController.navigate("home") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            // Belum login
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Katalog Film", style = MaterialTheme.typography.headlineMedium)
    }
}

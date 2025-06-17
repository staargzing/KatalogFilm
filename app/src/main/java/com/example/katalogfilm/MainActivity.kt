package com.example.katalogfilm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.katalogfilm.ui.navigation.AppNavigation
import com.example.katalogfilm.ui.theme.KatalogFilmTheme
import androidx.navigation.compose.rememberNavController
import com.example.katalogfilm.ui.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KatalogFilmTheme {
                val navController = rememberNavController() // ⬅️ Dideklarasikan dulu
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen(navController = navController) // Scaffold + Navigation
                }
            }
        }
    }

}


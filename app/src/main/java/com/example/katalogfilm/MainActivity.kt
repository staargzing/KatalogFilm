package com.example.katalogfilm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.katalogfilm.ui.navigation.NavGraph
import com.example.katalogfilm.ui.theme.KatalogFilmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KatalogFilmTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

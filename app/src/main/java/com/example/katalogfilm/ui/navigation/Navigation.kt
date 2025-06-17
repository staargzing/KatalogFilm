package com.example.katalogfilm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.katalogfilm.ui.login.LoginScreen
import com.example.katalogfilm.ui.detail.DetailScreen
import com.example.katalogfilm.ui.favorite.FavoriteScreen
import com.example.katalogfilm.ui.home.HomeScreen
import com.example.katalogfilm.ui.profile.ProfileScreen
import com.example.katalogfilm.ui.register.RegisterScreen
import com.example.katalogfilm.ui.splash.SplashScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("favorite") {
            FavoriteScreen()
        }
        composable("profile") {
            ProfileScreen()
        }
        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            DetailScreen(movieId)
        }
    }
}

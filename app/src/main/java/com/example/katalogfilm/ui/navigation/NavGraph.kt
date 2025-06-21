package com.example.katalogfilm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.katalogfilm.ui.detail.DetailScreen
import com.example.katalogfilm.ui.favorite.FavoriteScreen
import com.example.katalogfilm.ui.home.HomeScreen
import com.example.katalogfilm.ui.profile.ProfileScreen
import com.example.katalogfilm.ui.splash.SplashScreen
import com.example.katalogfilm.ui.login.LoginScreen
import com.example.katalogfilm.ui.register.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AuthScreen.Splash.route
    ) {
        // AUTHENTICATION
        composable(AuthScreen.Splash.route) {
            SplashScreen(navController)
        }
        composable(AuthScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(AuthScreen.Register.route) {
            RegisterScreen(navController)
        }

        // MAIN PAGES
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Favorite.route) {
            FavoriteScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        // DETAIL PAGE
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            DetailScreen(
                movieId = movieId,
                navController = navController // ✅ wajib disertakan
            )
        }
    }
}

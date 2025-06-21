package com.example.katalogfilm.ui.profile

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.katalogfilm.data.local.AppDatabase
import com.example.katalogfilm.datastore.SessionManager
import com.example.katalogfilm.viewmodel.ProfileViewModel
import com.example.katalogfilm.viewmodel.ProfileViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context) }
    val viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(db.userDao())
    )
    val scope = rememberCoroutineScope()

    val user by viewModel.user.collectAsState()

    LaunchedEffect(true) {
        val userId = SessionManager.getUserId(context)
        if (userId != null) {
            viewModel.loadUser(userId)
        } else {
            navController.navigate("login") {
                popUpTo("profile") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Profil", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Text("Nama: ${user?.name ?: "-"}")
        Text("Email: ${user?.email ?: "-"}")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            scope.launch {
                SessionManager.clearSession(context)
                Toast.makeText(context, "Logout berhasil", Toast.LENGTH_SHORT).show()
                navController.navigate("login") {
                    popUpTo("profile") { inclusive = true }
                }
            }
        }) {
            Text("Logout")
        }
    }
}

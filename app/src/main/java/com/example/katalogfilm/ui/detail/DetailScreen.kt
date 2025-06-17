package com.example.katalogfilm.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import coil.compose.rememberAsyncImagePainter
import com.example.katalogfilm.viewmodel.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    movieId: Int,
    viewModel: HomeViewModel = viewModel()
) {
    val movie = viewModel.movies.collectAsState().value.find { it.id == movieId }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detail Film") })
        }
    ) { padding ->
        movie?.let {
            Column(modifier = Modifier
                .padding(padding)
                .padding(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${it.posterPath}"),
                    contentDescription = it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.title, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.overview, style = MaterialTheme.typography.bodyMedium)
            }
        } ?: run {
            Text("Film tidak ditemukan", modifier = Modifier.padding(16.dp))
        }
    }
}

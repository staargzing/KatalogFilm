package com.example.katalogfilm.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.katalogfilm.data.local.AppDatabase
import com.example.katalogfilm.data.local.Favorite
import com.example.katalogfilm.viewmodel.FavoriteViewModel
import com.example.katalogfilm.viewmodel.FavoriteViewModelFactory
import com.example.katalogfilm.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    movieId: Int,
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getInstance(context) }

    val favoriteViewModel: FavoriteViewModel = viewModel(
        factory = FavoriteViewModelFactory(db.favoriteDao())
    )

    val movie = viewModel.movies.collectAsState().value.find { it.id == movieId }
    var isFavorite by remember { mutableStateOf(false) }

    // Cek status favorit saat screen dibuka
    LaunchedEffect(movieId) {
        isFavorite = favoriteViewModel.isFavorite(movieId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Film") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { padding ->
        movie?.let {
            Column(
                modifier = Modifier
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
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val favorite = Favorite(
                            movieId = it.id,
                            title = it.title,
                            posterPath = it.posterPath,
                            overview = it.overview
                        )
                        if (isFavorite) {
                            favoriteViewModel.removeFromFavorite(favorite)
                        } else {
                            favoriteViewModel.addToFavorite(favorite)
                        }
                        isFavorite = !isFavorite
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isFavorite) "Hapus dari Favorit" else "Tambahkan ke Favorit")
                }
            }
        } ?: run {
            Text(
                "Film tidak ditemukan",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

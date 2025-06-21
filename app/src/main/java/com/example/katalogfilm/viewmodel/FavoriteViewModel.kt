package com.example.katalogfilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogfilm.data.local.Favorite
import com.example.katalogfilm.data.local.FavoriteDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteDao: FavoriteDao
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites: StateFlow<List<Favorite>> = _favorites

    init {
        viewModelScope.launch {
            favoriteDao.getAllFavorites()
                .collect { list ->
                    _favorites.value = list
                }
        }
    }

    fun addToFavorite(favorite: Favorite) {
        viewModelScope.launch {
            favoriteDao.insertFavorite(favorite)
        }
    }

    fun removeFromFavorite(favorite: Favorite) {
        viewModelScope.launch {
            favoriteDao.deleteFavorite(favorite)
        }
    }

    suspend fun isFavorite(movieId: Int): Boolean {
        return favoriteDao.isFavorite(movieId)
    }
}

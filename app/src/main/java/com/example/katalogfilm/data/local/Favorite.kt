package com.example.katalogfilm.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val movieId: Int,
    val title: String,
    val posterPath: String,
    val overview: String
)

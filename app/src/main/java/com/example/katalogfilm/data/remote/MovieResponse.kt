package com.example.katalogfilm.data.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse(
	@SerializedName("results")
	val results: List<Movie>
)

data class Movie(
	@SerializedName("id")
	val id: Int,

	@SerializedName("title")
	val title: String,

	@SerializedName("overview")
	val overview: String,

	@SerializedName("poster_path")
	val posterPath: String
)

package com.example.moviesdbapi.domain.movie.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("Search") val results: List<Movie>)
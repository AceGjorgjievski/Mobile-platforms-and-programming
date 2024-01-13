package com.example.moviesdbapi.domain.movie

import com.example.moviesdbapi.domain.movie.model.Movie

interface RemoteMovieDataSource {
    suspend fun search(query: String): List<Movie>
}
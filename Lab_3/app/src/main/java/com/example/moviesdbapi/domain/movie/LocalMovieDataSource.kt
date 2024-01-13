package com.example.moviesdbapi.domain.movie

import com.example.moviesdbapi.domain.movie.model.Movie

interface LocalMovieDataSource {

    suspend fun insert(movie: Movie)

    suspend fun saveAll(movies: List<Movie>)

    suspend fun delete(id: String)

    suspend fun getAll(): List<Movie>

    suspend fun searchMovies(query: String): List<Movie>
}
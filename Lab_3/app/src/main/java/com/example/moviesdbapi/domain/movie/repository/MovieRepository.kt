package com.example.moviesdbapi.domain.movie.repository

import com.example.moviesdbapi.domain.movie.LocalMovieDataSource
import com.example.moviesdbapi.domain.movie.RemoteMovieDataSource
import com.example.moviesdbapi.domain.movie.model.Movie
import com.example.moviesdbapi.utils.NetworkConnectivity

class MovieRepository(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource,
    private val networkConnectivity: NetworkConnectivity

) {
    suspend fun queryMovies(query: String): List<Movie> {
        if (networkConnectivity.isNetworkAvailable) {
            return remoteMovieDataSource.search(query)
                .apply { localMovieDataSource.saveAll(this) }
        }
        return localMovieDataSource.searchMovies(query)
    }

    suspend fun listMovies(): List<Movie> {
        return localMovieDataSource.getAll()
    }

}
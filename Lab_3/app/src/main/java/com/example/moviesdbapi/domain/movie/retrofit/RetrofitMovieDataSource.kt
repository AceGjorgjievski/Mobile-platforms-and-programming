package com.example.moviesdbapi.domain.movie.retrofit

import com.example.moviesdbapi.domain.movie.RemoteMovieDataSource
import com.example.moviesdbapi.domain.movie.model.Movie

class RetrofitMovieDataSource(private val movieDbApi: MovieDbApi): RemoteMovieDataSource {
    override suspend fun search(query: String): List<Movie> {
        val movieResponse = movieDbApi.searchMovies(query)
        val responseBody = movieResponse.body()
        if(movieResponse.isSuccessful && responseBody != null) {
            if(responseBody.results != null) {
                return responseBody.results
            } else {
                throw Exception("Error searching movies")
            }
        }
        throw Exception("Error searching movies")
    }
}
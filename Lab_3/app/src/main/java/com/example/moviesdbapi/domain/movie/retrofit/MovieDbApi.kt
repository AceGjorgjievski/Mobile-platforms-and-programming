package com.example.moviesdbapi.domain.movie.retrofit

import com.example.moviesdbapi.domain.movie.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {

    @GET("/")
    suspend fun searchMovies(@Query("s") query: String): Response<MovieResponse>
}
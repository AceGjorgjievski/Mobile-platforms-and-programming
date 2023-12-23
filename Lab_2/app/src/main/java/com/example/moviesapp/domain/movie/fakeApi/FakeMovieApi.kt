package com.example.moviesapp.domain.movie.fakeApi

import com.example.moviesapp.domain.movie.model.Movie

class FakeMovieApi(
) {
    private val movies: MutableList<Movie> = ArrayList<Movie>();

    fun addAllMovies(newMovies: MutableList<Movie>) {
        movies.addAll(newMovies);
    }

    fun addMovie(movie: Movie) {
        movies.add(movie);
    }

    fun getAllMovies(): MutableList<Movie> {
        return movies;
    }
}
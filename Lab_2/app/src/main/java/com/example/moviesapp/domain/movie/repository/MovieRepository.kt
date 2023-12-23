package com.example.moviesapp.domain.movie.repository

import com.example.moviesapp.domain.movie.fakeApi.FakeMovieApi
import com.example.moviesapp.domain.movie.model.Actor
import com.example.moviesapp.domain.movie.model.Movie

class MovieRepository(private val fakeMovieApi: FakeMovieApi) {

    public fun getAllMovies() : MutableList<Movie>{
        return this.fakeMovieApi.getAllMovies();
    }

    public fun addMovie(name: String,
                        description: String,
                        director: String,
                        actors: MutableList<Actor>): Movie {
        val movie = Movie(
            (fakeMovieApi.getAllMovies().size + 1).toLong(),
            name,
            description,
            director,
            actors
        )

        this.fakeMovieApi.addMovie(movie);

        return movie;
    }

    public fun getMovie(movieId: Long): Movie {
        val allMovies =  this.fakeMovieApi.getAllMovies();
        val movie = allMovies.find { movie -> movie.id.toString() == movieId.toString() }
        return movie!!
    }
}
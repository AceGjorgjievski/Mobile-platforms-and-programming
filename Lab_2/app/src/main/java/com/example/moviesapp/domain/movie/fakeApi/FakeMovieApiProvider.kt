package com.example.moviesapp.domain.movie.fakeApi

import com.example.moviesapp.domain.movie.model.Actor
import com.example.moviesapp.domain.movie.model.Movie

class FakeMovieApiProvider {

    companion object {
        @Volatile
        private var INSTANCE: FakeMovieApi?= null;

        @JvmStatic
        fun getFakeMovieApi():FakeMovieApi {
            return INSTANCE ?: synchronized(this) {
                val instance = createFakeMovieApi();
                INSTANCE = instance;
                instance
            }
        }

        private fun createFakeMovieApi(): FakeMovieApi {
            val fakeMovieApi = FakeMovieApi();

            for (i in 1..10) {
                val movie = Movie(
                    id = i.toLong(),
                    name = "Movie - $i",
                    description = "Description - $i",
                    director = "Director - $i",
                    actors = generateRandomActors()
                )
                fakeMovieApi.addMovie(movie)
            }

            return fakeMovieApi
        }

        private fun generateRandomActors(): MutableList<Actor> {
            val numberOfActors = (1..3).random()
            val actors = mutableListOf<Actor>()

            for (i in 1..numberOfActors) {
                actors.add(Actor(id = i.toLong(), name = "Name - $i"))
            }

            return actors
        }
    }
}
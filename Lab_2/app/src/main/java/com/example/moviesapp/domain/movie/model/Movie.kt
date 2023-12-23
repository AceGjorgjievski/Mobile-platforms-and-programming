package com.example.moviesapp.domain.movie.model

data class Movie(
    val id: Long,
    val name: String,
    val description: String,
    val director: String,
    val actors: MutableList<Actor>
)
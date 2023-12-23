package com.example.moviesapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.movie.fakeApi.FakeMovieApi
import com.example.moviesapp.domain.movie.fakeApi.FakeMovieApiProvider
import com.example.moviesapp.domain.movie.repository.MovieRepository

class MoviesViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(
                MovieRepository(
                    FakeMovieApiProvider.getFakeMovieApi()
                )
            )
    }
}
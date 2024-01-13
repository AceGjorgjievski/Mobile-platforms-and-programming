package com.example.moviesdbapi.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdbapi.domain.movie.model.Movie

class MovieViewModel: ViewModel() {
    private val _mutableLiveData = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _mutableLiveData

    fun selectMovie(movie: Movie) {
        this._mutableLiveData.value = movie
    }

}
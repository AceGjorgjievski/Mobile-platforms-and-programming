package com.example.moviesdbapi.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesdbapi.domain.movie.model.Movie
import com.example.moviesdbapi.domain.movie.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()

    fun getMoviesLiveData(): LiveData<List<Movie>> = _moviesLiveData

    fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.queryMovies(query)
            _moviesLiveData.postValue(movies)
        }
    }

    fun listAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.listMovies()
            _moviesLiveData.postValue(movies)
        }
    }
}
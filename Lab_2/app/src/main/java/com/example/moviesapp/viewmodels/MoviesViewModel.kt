package com.example.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.movie.model.Movie
import com.example.moviesapp.domain.movie.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val moviesLiveData = MutableLiveData<MutableList<Movie>>();

    fun getMoviesLiveData(): LiveData<MutableList<Movie>> = moviesLiveData;

    fun getAllMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.getAllMovies();
            moviesLiveData.postValue(movies);
        }
    }

}
package com.example.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.movie.model.Movie
import com.example.moviesapp.domain.movie.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val movieDetailsLiveData = MutableLiveData<Movie>();

    fun getDetailsForMovieLiveData() : LiveData<Movie> = movieDetailsLiveData;

    fun showDetailsForMovie(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = movieRepository.getMovie(movieId)
            movieDetailsLiveData.postValue(movie);
        }
    }

}
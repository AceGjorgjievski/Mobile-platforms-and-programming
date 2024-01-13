package com.example.moviesdbapi.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesdbapi.domain.movie.repository.MovieRepository
import com.example.moviesdbapi.domain.movie.retrofit.MovieDbApiProvider
import com.example.moviesdbapi.domain.movie.retrofit.RetrofitMovieDataSource
import com.example.moviesdbapi.domain.movie.room.AppDatabase
import com.example.moviesdbapi.domain.movie.room.RoomMovieDataSource
import com.example.moviesdbapi.utils.NetworkConnectivity

class MoviesViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(
                MovieRepository(
                    RetrofitMovieDataSource(
                        MovieDbApiProvider.getMovieDbApi()
                    ),
                    RoomMovieDataSource(
                        AppDatabase.getDatabase(context).movieDao()
                    ),
                    NetworkConnectivity(context)
                )
            )
    }
}
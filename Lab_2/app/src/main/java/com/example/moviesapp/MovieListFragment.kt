package com.example.moviesapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.adapters.MovieListViewAdapter
import com.example.moviesapp.databinding.FragmentMovieListBinding
import com.example.moviesapp.dialogs.AddNewMovieDialog
import com.example.moviesapp.viewmodels.MoviesViewModel
import com.example.moviesapp.viewmodels.MoviesViewModelFactory


class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private var _binding: FragmentMovieListBinding?=null;
    private val binding get() = _binding!!;

    private lateinit var moviesViewModel: MoviesViewModel;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieListBinding.bind(view);

        val viewModelFactory = MoviesViewModelFactory(requireContext());
        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]
        ////
        var movieListViewAdapter: MovieListViewAdapter = MovieListViewAdapter();
        binding.rvMovieList.adapter = movieListViewAdapter;

        moviesViewModel.getMoviesLiveData().observe(viewLifecycleOwner) {
            movieListViewAdapter.updateMovies(it);
        }

        moviesViewModel.getAllMovies();


        //click movie items


        //Add new movie
        binding.btnAddNewMovie.setOnClickListener {
            AddNewMovieDialog().show(childFragmentManager, "add-new-movie-dialog")
        }
    }

}
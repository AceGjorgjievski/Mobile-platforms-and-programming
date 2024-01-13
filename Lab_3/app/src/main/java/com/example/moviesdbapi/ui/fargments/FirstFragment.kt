package com.example.moviesdbapi.ui.fargments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.moviesdbapi.R
import com.example.moviesdbapi.adapters.MovieAdapter
import com.example.moviesdbapi.databinding.FragmentFirstBinding
import com.example.moviesdbapi.ui.viewmodels.MovieViewModel
import com.example.moviesdbapi.ui.viewmodels.MoviesViewModel
import com.example.moviesdbapi.ui.viewmodels.MoviesViewModelFactory


class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding?= null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by activityViewModels()

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this._binding = FragmentFirstBinding.bind(view)

//        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        val moviesViewModelFactory = MoviesViewModelFactory(requireContext())
        moviesViewModel = ViewModelProvider(this, moviesViewModelFactory)[MoviesViewModel::class.java]

        var adapter: MovieAdapter = MovieAdapter()
        binding.list.adapter = adapter

        moviesViewModel.getMoviesLiveData().observe(viewLifecycleOwner) {
//            adapter = MovieAdapter(ArrayList(it))
            adapter.updateMovies(it)
//            binding.list.adapter = adapter
        }

        binding.button.setOnClickListener {
            moviesViewModel.search(binding.editQuery.text.toString())
        }

        moviesViewModel.listAll()

        adapter.onItemClicked = {
            movieViewModel.selectMovie(it)
            parentFragmentManager.commit {
                replace(R.id.fragmentContainerView, SecondFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }



}
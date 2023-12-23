package com.example.moviesapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.moviesapp.databinding.FragmentMovieDetailsBinding
import com.example.moviesapp.viewmodels.MovieDetailsViewModel

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding?= null;
    private val binding get() = _binding!!;
    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailsBinding.bind(view)

        movieDetailsViewModel.getDetailsForMovieLiveData().observe(viewLifecycleOwner) {
            binding.textViewMovieName.text = it.name
            binding.textViewDescription.text = it.description
            binding.textViewDirector.text = it.director
        }
    }
}
package com.example.moviesdbapi.ui.fargments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.moviesdbapi.R
import com.example.moviesdbapi.databinding.FragmentSecondBinding
import com.example.moviesdbapi.ui.viewmodels.MovieViewModel


class SecondFragment : Fragment(R.layout.fragment_second) {

    private val movieViewModel: MovieViewModel by activityViewModels()
    private var _binding : FragmentSecondBinding?= null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSecondBinding.bind(view)

        movieViewModel.movie.observe(viewLifecycleOwner) { movie ->
            binding.movieTitleDetail.text = movie.title
            binding.movieYearDetail.text = movie.year

            Glide.with(requireContext())
                .load(movie.image)
                .into(binding.movieImageDetail)
        }

    }

}
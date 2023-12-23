package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.commit
import com.example.moviesapp.dialogs.AddNewMovieDialog
import com.example.moviesapp.domain.movie.fakeApi.FakeMovieApiProvider
import com.example.moviesapp.domain.movie.model.Actor
import com.example.moviesapp.domain.movie.repository.MovieRepository

class MainActivity : AppCompatActivity(), AddNewMovieDialog.AddNewMovieDialogListener {

    private lateinit var movieRepository: MovieRepository;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, MovieListFragment())
                setReorderingAllowed(true)

            }
        }

        this.movieRepository = MovieRepository(FakeMovieApiProvider.getFakeMovieApi())
    }

    override fun onDialogPositiveClick(
        movieName: String,
        movieDescription: String,
        movieDirector: String,
        actors: MutableList<Actor>
    ) {
        movieRepository.addMovie(movieName,movieDescription,movieDirector,actors);
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, MovieListFragment())
            setReorderingAllowed(true)
        }
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        dialog.dismiss()
    }
}
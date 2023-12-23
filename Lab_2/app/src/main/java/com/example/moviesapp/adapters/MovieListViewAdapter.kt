package com.example.moviesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.domain.movie.model.Movie

class MovieListViewAdapter(private val data:MutableList<Movie> = ArrayList<Movie>()):
    RecyclerView.Adapter<MovieListViewAdapter.MovieViewHolder>() {

    /**
     * showing all the variables(components) needed for one particular item
     */
    class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var movieIdTextView: TextView;
        val movieNameTextView: TextView;
        val directorMovieTextView: TextView;

        private var currentMovie: Movie?
        var currentString: String?= null;
        init {
//            textView = view.findViewById(R.id.txtOutput);
            movieIdTextView = view.findViewById(R.id.movieIdTextView);
            movieNameTextView = view.findViewById(R.id.movieNameTextView);
            directorMovieTextView = view.findViewById(R.id.movieDirectorNameTextView);
            currentMovie = null;
        }

        fun bind(currentMovie: Movie) {
            this.currentString = currentString;
//            this.textView.text = currentString;
            this.currentMovie = currentMovie;
            movieIdTextView.text = "${currentMovie.id}"
            movieNameTextView.text = currentMovie.name
            directorMovieTextView.text = currentMovie.director;

            //click item
        }

    }

    /**
     * koj e layout-ot na stavkite koj shto kje bidat renderirani vo
     * ramki na ovoj adapter
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieViewHolder(view);
    }

    override fun getItemCount(): Int {
        return data.size;
    }

    /**
     * povrzuvanje na view-ata (kako shto e textView-to) so soodveniot podatok
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position]);
    }

    fun updateMovies(movies: MutableList<Movie>) {
        this.data.clear()
        if(movies != null) {
            this.data.addAll(movies);
        }

        notifyDataSetChanged();
    }
}
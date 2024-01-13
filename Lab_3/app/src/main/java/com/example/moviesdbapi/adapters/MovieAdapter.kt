package com.example.moviesdbapi.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesdbapi.R
import com.example.moviesdbapi.domain.movie.model.Movie

class MovieAdapter(private val movies: ArrayList<Movie> = ArrayList<Movie>())
    :RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var onItemClicked : ((Movie) -> Unit)?= null

    class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){
        private var imageView: ImageView = view.findViewById(R.id.movie_image)
        private var titleText: TextView = view.findViewById(R.id.movie_title)
        private var yearText: TextView = view.findViewById(R.id.movie_year)

        fun bind(movie: Movie){
            Glide.with(imageView)
                .load(movie.image)
                .centerCrop().placeholder(R.drawable.baseline_movie_creation_24)
                .into(imageView)

            titleText.text = movie.title
            yearText.text = movie.year
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            this.onItemClicked?.invoke(movies[position])
        }
    }

    fun updateMovies(newMovies: List<Movie>){
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

}

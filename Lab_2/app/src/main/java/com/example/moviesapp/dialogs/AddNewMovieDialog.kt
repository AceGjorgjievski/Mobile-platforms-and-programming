package com.example.moviesapp.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.moviesapp.R
import com.example.moviesapp.domain.movie.model.Actor
import kotlin.ClassCastException

class AddNewMovieDialog: DialogFragment() {

    interface AddNewMovieDialogListener {
        fun onDialogPositiveClick(movieName: String,
                                  movieDescription: String,
                                  movieDirector: String,
                                  actors: MutableList<Actor>)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    lateinit var listener: AddNewMovieDialogListener;

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return activity?.let {
//            val builder = AlertDialog.Builder(it)
//            builder.setMessage(R.string.add_new_movie)
//                .setPositiveButton(R.string.add, DialogInterface.OnClickListener{dialog, id -> })
//                .setNegativeButton(R.string.back, DialogInterface.OnClickListener{dialog, id ->})
//            builder.create()
//        }?:throw IllegalStateException("Activity can not be null")
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            var inflater = requireActivity().layoutInflater
            val view: View = inflater.inflate(R.layout.add_movie_dialog, null)

            val editMovieName = view.findViewById<EditText>(R.id.editMovieName)
            val editDescription = view.findViewById<EditText>(R.id.editDescription)
            val editDirector = view.findViewById<EditText>(R.id.editDirector)
            val editActorName = view.findViewById<EditText>(R.id.editActorName)
            builder.setView(view)
                .setPositiveButton(R.string.add_new_movie, DialogInterface.OnClickListener{dialog, id ->
                    val movieName = editMovieName.text.toString()
                    val description = editDescription.text.toString()
                    val director = editDirector.text.toString()
                    val actorNames = editActorName.text.toString().split(",").map { it.trim() }

                    val actors = mutableListOf<Actor>()
                    for ((index, actorName) in actorNames.withIndex()) {
                        actors.add(Actor(index.toLong(), actorName))
                    }
                    listener.onDialogPositiveClick(movieName, description, director, actors)
                })
                .setNegativeButton(R.string.back, DialogInterface.OnClickListener{dialog, id ->
                    listener.onDialogNegativeClick(this)
                })
            builder.create()
        }?:throw IllegalStateException("Activity can not be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as AddNewMovieDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("${context.toString()} must implement AddNewMovieDialogListener")
        }
    }
}
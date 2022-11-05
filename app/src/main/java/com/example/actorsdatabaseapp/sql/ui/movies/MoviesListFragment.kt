package com.example.actorsdatabaseapp.sql.ui.movies

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actorsdatabaseapp.MyApplication
import com.example.actorsdatabaseapp.databinding.FragmentMoviesListBinding
import com.example.actorsdatabaseapp.sql.data.data_base.DataBaseHelper
import com.example.actorsdatabaseapp.sql.data.model.ActorMovies
import com.example.actorsdatabaseapp.sql.ui.adapter.MoviesAdapter

class MoviesListFragment : Fragment() {

    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private val dataBaseHelper by lazy {
        (requireActivity().application as MyApplication).dbHelper
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesAdapter = MoviesAdapter { action, movie ->
            when (action) {
                MoviesAdapter.ActionEnum.ACTION_DELETE_MOVIE -> {
                    deleteMovie(movie)
                }
            }
            moviesAdapter.updateData(getMoviesList())
        }
        moviesAdapter.movies = getMoviesList()
        binding.moviesRecyclerView.apply {
            visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = moviesAdapter
        }
    }

    private fun getMoviesList(): ArrayList<ActorMovies> {
        val databaseHandler = DataBaseHelper(requireActivity())
        return databaseHandler.getMovieList()
    }

    private fun deleteMovie(movie: ActorMovies) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Delete Movie")
            setMessage("Are You sure you want to delete ${movie.movieName}")
            setIcon(android.R.drawable.ic_dialog_alert)
            setPositiveButton("Yes") { dialogInterface, _ ->
                val dataBaseHelper = DataBaseHelper(requireActivity())
                val status = dataBaseHelper.deleteMovie(movie.movieId)
                if (status > -1) {
                    Toast.makeText(requireActivity().applicationContext, "Movie is deleted", Toast.LENGTH_SHORT).show()
                }
                getMoviesList()
                dialogInterface.dismiss()
            }
            setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            create()
            setCancelable(false)
        }.show()
    }

    companion object {
        fun newInstance() = MoviesListFragment()
    }
}


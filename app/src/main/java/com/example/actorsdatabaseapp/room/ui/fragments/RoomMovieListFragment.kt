package com.example.actorsdatabaseapp.room.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actorsdatabaseapp.databinding.FragmentRoomMovieListBinding
import com.example.actorsdatabaseapp.room.data.model.ActorWithMovies
import com.example.actorsdatabaseapp.room.data.model.MovieRoom
import com.example.actorsdatabaseapp.room.ui.adapters.RoomMovieAdapter
import com.example.actorsdatabaseapp.room.viewmodel.MovieViewModel

class RoomMovieListFragment : Fragment() {

    private lateinit var binding: FragmentRoomMovieListBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: RoomMovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomMovieListBinding.inflate(inflater, container, false)
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = RoomMovieAdapter { action, movie ->
            when (action) {
                RoomMovieAdapter.ActionEnum.ACTION_DELETE_MOVIE -> {
                    deleteMovie(movie)
                }
            }
        }
        movieViewModel.getAllActorWithMovies.observe(viewLifecycleOwner) { movie ->
            movieAdapter.updateData(movie.flatMap { aa -> mutableListOf<MovieRoom>().also { it.addAll(aa.movieList) } })
        }
        binding.roomMoviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = movieAdapter
        }
    }

    private fun deleteMovie(movieRoom: MovieRoom) {
        AlertDialog.Builder(requireContext()).apply {
            setPositiveButton("Yes") { _, _ ->
                movieViewModel.deleteMovie(movieRoom)
                Toast.makeText(requireContext(), "Successfully removed ${movieRoom.movieName}", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("No") { _, _ -> }
            setIcon(android.R.drawable.ic_dialog_alert)
            setTitle("Delete ${movieRoom.movieName}")
            setMessage("Are You sure you want to delete ${movieRoom.movieName}")
            create().show()
        }
    }

    companion object {
        fun newInstance() = RoomMovieListFragment()
    }
}







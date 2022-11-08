package com.example.actorsdatabaseapp.room.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actorsdatabaseapp.databinding.FragmentRoomMovieListBinding
import com.example.actorsdatabaseapp.room.ui.adapters.RoomMovieAdapter
import com.example.actorsdatabaseapp.room.viewmodel.movie.MovieViewModel

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
//                    deleteMovie(movie)
                }
            }
//            movieViewModel.getAllActorWithMovies.observe(viewLifecycleOwner) { actor ->
//                movieAdapter.updateData(actor)
        }
        binding.roomMoviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = movieAdapter
        }
    }

    companion object {
        fun newInstance() = RoomMovieListFragment()
    }
}

//    private fun deleteMovie(actorWithMovies: ActorWithMovies) {
//        AlertDialog.Builder(requireContext()).apply {
//            setPositiveButton("Yes") { _, _ ->
//                movieViewModel.deleteMovie(actorWithMovies)
//                Toast.makeText(requireContext(), "Successfully removed ${actorWithMovies}", Toast.LENGTH_SHORT).show()
//            }
//            setNegativeButton("No") { _, _ -> }
//            setIcon(android.R.drawable.ic_dialog_alert)
//            setTitle("Delete ${actorWithMovies}")
//            setMessage("Are You sure you want to delete ${actorWithMovies}")
//            create().show()
//        }
//    }
//




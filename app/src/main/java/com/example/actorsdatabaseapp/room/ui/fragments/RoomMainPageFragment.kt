package com.example.actorsdatabaseapp.room.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actorsdatabaseapp.R
import com.example.actorsdatabaseapp.databinding.FragmentRoomMainPageBinding
import com.example.actorsdatabaseapp.room.data.model.ActorRoom
import com.example.actorsdatabaseapp.room.data.model.MovieRoom
import com.example.actorsdatabaseapp.room.data.model.Pet
import com.example.actorsdatabaseapp.room.ui.adapters.RoomActorsAdapter
import com.example.actorsdatabaseapp.room.viewmodel.ActorViewModel

class RoomMainPageFragment : Fragment() {
    private lateinit var binding: FragmentRoomMainPageBinding
    private lateinit var actorViewModel: ActorViewModel
    private lateinit var actorAdapter: RoomActorsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomMainPageBinding.inflate(inflater, container, false)
        actorViewModel = ViewModelProvider(this)[ActorViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.roomAddButton.setOnClickListener {
            insertActorsToDataBase()
        }
        setUpdDataIntoRecyclerview()
    }

    private fun showAddMovieDialog(id: Int) {
        Dialog(requireActivity()).apply {
            setContentView(R.layout.dialog_add_movie)
            findViewById<TextView>(R.id.actorsIdEditableTextView).setText(id.toString())

            findViewById<Button>(R.id.addMovieFB).setOnClickListener {
                val actorId = findViewById<TextView>(R.id.actorsIdEditableTextView).text.toString()
                val movieName = findViewById<EditText>(R.id.movieNameEditText).text.toString()
                val movieRate = findViewById<EditText>(R.id.movieRateEditText).text.toString()
                val movieYear = findViewById<EditText>(R.id.movieYearEditText).text.toString()

//                val movies = ArrayList<MovieRoom>()
//                movies.add(MovieRoom(actorId = actorId.toInt(), movieName = movieName, imdbRate = movieRate.toDouble(), year = movieYear.toInt()))
                actorViewModel.addMovie(MovieRoom(actorId = actorId.toInt(), movieName = movieName, imdbRate = movieRate.toDouble(), year = movieYear.toInt()))
                Toast.makeText(requireContext(), "New Movie Added", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }.show()
    }

    private fun insertActorsToDataBase() {
        val name = binding.roomNameEditText.text.toString()
        val surName = binding.roomSurNameEditText.text.toString()
        val age = binding.roomAgeEditText.text
        val petsName = binding.roomPetNameEditText.text.toString()
        val petsAge = binding.roomPetAgeEditText.text.toString()
        val isSmart = binding.roomIsSmartCheckBox.isChecked.toString()
        val pets = ArrayList<Pet>()
        if (petsName.isNotEmpty() && petsAge.isNotEmpty()) {
            pets.add(Pet(petsName, petsAge.toInt(), isSmart.toBoolean()))
        }
        if (inputCheck(name, surName, age)) {
            val actorRoom = ActorRoom(0, name, surName, Integer.parseInt(age.toString()), pets)
            actorViewModel.addActor(actorRoom)
            Toast.makeText(requireActivity().applicationContext, "Actors saved", Toast.LENGTH_SHORT).show()
            binding.roomNameEditText.text.clear()
            binding.roomSurNameEditText.text.clear()
            binding.roomAgeEditText.text.clear()
            binding.roomPetNameEditText.text.clear()
            binding.roomPetAgeEditText.text.clear()
            binding.roomIsSmartCheckBox.clearFocus()
        } else {
            Toast.makeText(requireActivity().applicationContext, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, surName: String, age: Editable) =
        (name.isNotEmpty() && surName.isNotEmpty() && age.isNotEmpty())

    private fun deleteActor(actorRoom: ActorRoom) {
        AlertDialog.Builder(requireContext()).apply {
            setPositiveButton("Yes") { _, _ ->
                actorViewModel.deleteActor(actorRoom)
                Toast.makeText(requireContext(), "Successfully removed ${actorRoom.name}", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("No") { _, _ -> }
            setIcon(android.R.drawable.ic_dialog_alert)
            setTitle("Delete ${actorRoom.name}")
            setMessage("Are You sure you want to delete ${actorRoom.name}")
            create().show()
        }
    }

    private fun setUpdDataIntoRecyclerview() {
        actorAdapter = RoomActorsAdapter { action, actor ->
            when (action) {
                RoomActorsAdapter.ActionEnum.ACTION_DELETE -> {
                    deleteActor(actor)
                }
                RoomActorsAdapter.ActionEnum.ACTION_ADD_MOVIE -> {
                    showAddMovieDialog(actor.id)
                }
                RoomActorsAdapter.ActionEnum.ACTION_ADD_PET -> {

                }
            }
        }
        actorViewModel.getAllActors.observe(viewLifecycleOwner) { actor ->
            actorAdapter.updateData(actor)
        }
        binding.roomActorsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = actorAdapter
        }
    }

    companion object {
        fun newInstance() = RoomMainPageFragment()
    }
}
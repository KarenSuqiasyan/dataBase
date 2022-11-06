package com.example.actorsdatabaseapp.room.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actorsdatabaseapp.databinding.FragmentRoomMainPageBinding
import com.example.actorsdatabaseapp.room.data.model.ActorRoom
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

    private fun insertActorsToDataBase() {
        val name = binding.roomNameEditText.text.toString()
        val surName = binding.roomSurNameEditText.text.toString()
        val age = binding.roomAgeEditText.text
        if (inputCheck(name, surName, age)) {
            val actorRoom = ActorRoom(0, name, surName, Integer.parseInt(age.toString()))
            actorViewModel.addActor(actorRoom)
            Toast.makeText(requireActivity().applicationContext, "Actors saved", Toast.LENGTH_SHORT).show()
            binding.roomNameEditText.text.clear()
            binding.roomSurNameEditText.text.clear()
            binding.roomAgeEditText.text.clear()
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
//                        showAddMovieDialog(actor.id)
                }
            }
        }
        actorViewModel.getAllActors.observe(viewLifecycleOwner, Observer { actor ->
            actorAdapter.updateData(actor)
        })
        binding.roomActorsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = actorAdapter
        }
    }


    companion object {
        fun newInstance() = RoomMainPageFragment()
    }
}
package com.example.actorsdatabaseapp.sql.ui.main_page

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actorsdatabaseapp.MyApplication
import com.example.actorsdatabaseapp.R
import com.example.actorsdatabaseapp.databinding.FragmentSqliteMainPageBinding
import com.example.actorsdatabaseapp.sql.data.data_base.DataBaseHelper
import com.example.actorsdatabaseapp.sql.data.model.Actor
import com.example.actorsdatabaseapp.sql.data.model.Movie
import com.example.actorsdatabaseapp.sql.data.model.Pets
import com.example.actorsdatabaseapp.sql.ui.adapter.ActorsAdapter

class SqliteMainPageFragment : Fragment() {

//    private lateinit var sqliteMainViewModel: SqliteMainPageViewModel
    private lateinit var binding: FragmentSqliteMainPageBinding
    private lateinit var actorAdapter: ActorsAdapter
    private val dataBaseHelper by lazy {
        (requireActivity().application as MyApplication).dbHelper
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        sqliteMainViewModel = ViewModelProvider(this)[SqliteMainPageViewModel::class.java]
        binding = FragmentSqliteMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            addActor()
        }
        setUpdDataIntoRecyclerview()
    }

    private fun setUpdDataIntoRecyclerview() {
        if (getActorsList().size > 0) {
            actorAdapter = ActorsAdapter { action, actor ->
                when (action) {
                    ActorsAdapter.ActionEnum.ACTION_DELETE -> {
                        deleteActor(actor)
                    }
                    ActorsAdapter.ActionEnum.ACTION_ADD_MOVIE -> {
                        showAddMovieDialog(actor.id)
                    }
                }
                actorAdapter.updateData(getActorsList())
            }
            actorAdapter.actors = getActorsList()
            binding.actorsRecyclerView.apply {
                visibility = View.VISIBLE
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = actorAdapter
            }
        } else {
            binding.actorsRecyclerView.visibility = View.GONE
        }
    }

    private fun addActor() {
        val name = binding.nameEditText.text.toString()
        val surName = binding.surNameEditText.text.toString()
        val age = binding.ageEditText.text.toString()
        val petName = binding.petNameEditText.text.toString()
        val petAge = binding.petAgeEditText.text.toString()
        val petIsSmart = binding.isSmartCheckBox
        val pet = Pets(petName, petAge.toInt(), petIsSmart.isChecked)
        val petList = ArrayList<Pets>()
        petList.add(pet)
        if (name.isNotEmpty() && surName.isNotEmpty() && age.isNotEmpty()) {
            val result = dataBaseHelper.addActor(Actor(name = name, age = age, surName = surName, pets = petList))
            if (result > 0) {
                Toast.makeText(requireActivity().applicationContext, "Actors saved to table: $result", Toast.LENGTH_SHORT).show()
                binding.nameEditText.text.clear()
                binding.surNameEditText.text.clear()
                binding.ageEditText.text.clear()
                binding.petNameEditText.text.clear()
                binding.petAgeEditText.text.clear()
                setUpdDataIntoRecyclerview()
            }
        }
    }

    private fun deleteActor(actor: Actor) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Delete Actor")
            setMessage("Are You sure you want to delete ${actor.name}")
            setIcon(android.R.drawable.ic_dialog_alert)
            setPositiveButton("Yes") { dialogInterface, _ ->
                val dataBaseHelper = DataBaseHelper(requireActivity())
                val status = dataBaseHelper.deleteActor(actor.id)
                if (status > -1) {
                    Toast.makeText(requireActivity().applicationContext, "Actor is deleted", Toast.LENGTH_SHORT).show()
                    setUpdDataIntoRecyclerview()
                }
                dialogInterface.dismiss()
            }
            setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            create()
            setCancelable(false)
        }.show()
    }

    private fun getActorsList(): ArrayList<Actor> {
        val databaseHandler = DataBaseHelper(requireActivity())
        return databaseHandler.getActors()
    }

    @SuppressLint("RestrictedApi")
    fun showAddMovieDialog(id: Int) {
        Dialog(requireActivity()).apply {
            setContentView(R.layout.dialog_add_movie)
            findViewById<TextView>(R.id.actorsIdEditableTextView).setText(id.toString())

            findViewById<Button>(R.id.addMovieFB).setOnClickListener {
                val actorId = findViewById<TextView>(R.id.actorsIdEditableTextView).text.toString()
                val movieName = findViewById<EditText>(R.id.movieNameEditText).text.toString()
                val movieRate = findViewById<EditText>(R.id.movieRateEditText).text.toString()
                val movieYear = findViewById<EditText>(R.id.movieYearEditText).text.toString()
                dataBaseHelper.addMovie(Movie(actorId = actorId.toInt(), movieName = movieName, year = movieYear.toInt(), imdbRate = movieRate.toDouble()))
                Toast.makeText(requireContext(), "New Movie Added", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }.show()
    }

    companion object {
        fun newInstance() = SqliteMainPageFragment()
    }
}

package com.example.actorsdatabaseapp.sql.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.actorsdatabaseapp.sql.ui.movies.MoviesListFragment
import com.example.actorsdatabaseapp.R
import com.example.actorsdatabaseapp.databinding.ActivityGeneralBinding
import com.example.actorsdatabaseapp.sql.ui.main_page.SqliteMainPageFragment

class GeneralActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGeneralBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneralBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.movie -> replaceFragment(MoviesListFragment.newInstance())
                R.id.actor -> replaceFragment(SqliteMainPageFragment.newInstance())
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()

    }
}
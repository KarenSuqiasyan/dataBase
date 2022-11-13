package com.example.actorsdatabaseapp.sql.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.actorsdatabaseapp.R
import com.example.actorsdatabaseapp.databinding.ActivitySqliteBinding
import com.example.actorsdatabaseapp.sql.ui.fragments.SqliteMainPageFragment
import com.example.actorsdatabaseapp.sql.ui.fragments.MoviesListFragment

class SQLiteActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySqliteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySqliteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.movie -> replaceFragment(MoviesListFragment.newInstance())
                R.id.actor -> replaceFragment(SqliteMainPageFragment.newInstance())
            }
            true
        }
        binding.bottomNavigationView.selectedItemId=R.id.actor
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
package com.example.actorsdatabaseapp.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.actorsdatabaseapp.R
import com.example.actorsdatabaseapp.databinding.ActivityRoomBinding
import com.example.actorsdatabaseapp.room.ui.fragments.RoomMainPageFragment
import com.example.actorsdatabaseapp.room.ui.fragments.RoomMovieListFragment

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.roomBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.movie -> replaceFragment(RoomMovieListFragment.newInstance())
                R.id.actor -> replaceFragment(RoomMainPageFragment.newInstance())
            }
            true
        }
        binding.roomBottomNavigationView.selectedItemId=R.id.actor
    }

    private fun replaceFragment(fragment: Fragment) {
         supportFragmentManager.beginTransaction().replace(R.id.roomContainer, fragment).commit()
    }
}
package com.example.kotlinstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kotlinstudy.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_hamburger_menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.setTitle("영화 목록")
        binding.navigation.setNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction().add(R.id.frame_layout_movie,MovieListFragment()).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.movie_list -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout_movie,MovieListFragment()).commit()
                Toast.makeText(this, "영화목록", Toast.LENGTH_SHORT).show()
            }
            R.id.movie_api -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout_movie,MovieBookFragment()).commit()
                Toast.makeText(this,"영화API",Toast.LENGTH_SHORT).show()
            }
            R.id.book ->{
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout_movie,MovieApiFragment()).commit()
                Toast.makeText(this,"영화예매",Toast.LENGTH_SHORT).show()
            }
        }
        binding.drawerLayout.closeDrawers()
        return false
    }
}


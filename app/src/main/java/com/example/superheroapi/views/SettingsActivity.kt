package com.example.superheroapi.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.superheroapi.MainActivity
import com.example.superheroapi.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_navigation.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        btmNav()
    }

    private fun btmNav() {
        bottomNavigationView.selectedItemId = R.id.settings
        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorites -> {
                    startActivity(Intent(applicationContext, FavoritesActivity::class.java))
                    overridePendingTransition(0,0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.home -> {

                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0,0)

                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
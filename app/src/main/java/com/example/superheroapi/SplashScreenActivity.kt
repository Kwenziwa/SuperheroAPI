package com.example.superheroapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.example.superheroapi.views.ProfileActivity

class SplashScreenActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        val handler = Handler()
        handler.postDelayed({ toHomeScreen() }, 9000)
    }

    private fun toHomeScreen() {

        //var intent: Intent? = null
        var intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent);

    }
}
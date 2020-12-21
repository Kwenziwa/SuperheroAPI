package com.example.superheroapi.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.superheroapi.R
import com.example.superheroapi.model.profile.ProfileModel
import com.example.superheroapi.repository.Repository
import com.example.superheroapi.ui.main.SuperHeroViewModel
import com.example.superheroapi.ui.main.SuperHeroViewModelFactory
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    private lateinit var viewModel: SuperHeroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        if (supportActionBar != null) {
            supportActionBar?.hide()
        }


        val intent = getIntent();
        val hero_id: String? = intent.getStringExtra("hero_id")

        val repository = Repository()
        val viewModelFactory = SuperHeroViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SuperHeroViewModel::class.java)
        if (hero_id != null) {
            viewModel.getProfile(hero_id)
        }
        viewModel.myProfile.observe(this, Observer { response ->
            if(response.isSuccessful){

                getProfileData(response)

            }else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })


        back_btn.setOnClickListener{
            onBackPressed()
        }
    }

    private fun getProfileData(response: Response<ProfileModel>?) {

         val profileModel: ProfileModel? = response?.body()

        if (profileModel != null) {

            // Image from url

            //Loading image from url into imageView
            Glide.with(this)
                .load(profileModel.image.url.toString())
                .placeholder(R.drawable.no_image_holder)
                .error(R.drawable.error_image)
                .into(hero_image);

            //biography
            superhero_name.text = profileModel.name
            full_name.text = profileModel.biography.full_name

            //powerstats
            intelligence_txt.text = profileModel.powerstats.intelligence
            strength_txt.text = profileModel.powerstats.strength
            speed_txt.text = profileModel.powerstats.speed
            power_txt.text = profileModel.powerstats.power

            //appearance
            sex_txt.text = profileModel.appearance.gender
            race_txt.text = profileModel.appearance.race
            eye_txt.text = profileModel.appearance.eye_color
            hair_txt.text = profileModel.appearance.hair_color

            // Work & Connections
            ccupation_txt.text = profileModel.work.occupation
            base_txt.text = profileModel.work.base
            group_affiliation_txt.text = profileModel.connections.group_affiliation
            relatives_txt.text = profileModel.connections.relatives

        }

    }




}
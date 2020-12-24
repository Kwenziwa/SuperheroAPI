 package com.example.superheroapi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapi.adapter.SuperheroAdatapter
import com.example.superheroapi.api.RetrofitInstance
import com.example.superheroapi.model.ErrorResposne.ErrorResponse
import com.example.superheroapi.model.charecter.ResultModel
import com.example.superheroapi.repository.Repository
import com.example.superheroapi.ui.main.SuperHeroViewModel
import com.example.superheroapi.ui.main.SuperHeroViewModelFactory
import com.example.superheroapi.ui.room.UserViewModel
import com.example.superheroapi.views.FavoritesActivity
import com.example.superheroapi.views.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_navigation.*


 class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SuperHeroViewModel
    private lateinit var userViewModel: UserViewModel
    private val myAdapter by lazy { SuperheroAdatapter() }
    private lateinit var errorResponse :ErrorResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        setupRecyclerview()
        searchHero()
        bottomNavigationView.selectedItemId = R.id.home

        btmNav()
        LottieSettings()

    }

    private fun btmNav() {
        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorites -> {

                    startActivity(Intent(applicationContext, FavoritesActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    startActivity(Intent(applicationContext, SettingsActivity::class.java))
                    overridePendingTransition(0, 0)
                }
                R.id.home -> {

                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun searchHero() {
        with(persistentSearchView) {
            setOnLeftBtnClickListener {
                // Handle the left button click
            }
            setOnClearInputBtnClickListener {
                // Handle the clear input button click
            }

            // Setting a delegate for the voice recognition input
            setVoiceRecognitionDelegate(VoiceRecognitionDelegate(this@MainActivity))

            setOnSearchConfirmedListener { searchView, query ->

                home_icon.visibility = View.VISIBLE

                if(query.toString() != null ){
                    getHeroList(query.toString())
                    onLeftButtonClicked()
                }
            }
            setSuggestionsDisabled(true)
        }
    }

    private fun getHeroList(name: String) {

        if(RetrofitInstance.isNetworkAvailable(this)){

            val repository = Repository()
            val viewModelFactory =SuperHeroViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(SuperHeroViewModel::class.java)
            viewModel.getSuperhero(name)
            viewModel.mySuperhero.observe(this, Observer { response ->
                if (response.isSuccessful) {

                    if (response.body()?.response.equals("success")) {

                        response.body()?.let { myAdapter.setData(it.results, this) }
                        home_icon.visibility = View.GONE
                        home_text.visibility = View.GONE
                    } else {

                        home_text.text = getString(R.string.empty_message)
                        home_icon.setAnimation(R.raw.empty_box)
                        myAdapter.setData(emptyList(), this)
                    }

                } else {

                    home_icon.setAnimation(R.raw.empty_box)
                    myAdapter.setData(emptyList(), this)
                }
            })

        }
        else{

            home_icon.setAnimation(R.raw.network_erro_hero)
            home_text.text = getString(R.string.network_message)
            myAdapter.setData(emptyList(), this)
        }

    }

    private fun setupRecyclerview() {

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onBackPressed() {
        if(persistentSearchView.isExpanded) {
            persistentSearchView.collapse()
            return
        }

        super.onBackPressed()
    }

    private fun onLeftButtonClicked() {
        onBackPressed()
    }

    private fun LottieSettings(){
        home_icon.playAnimation()
        home_icon.loop(true)
    }



}
package com.example.superheroapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapi.adapter.SuperheroAdatapter
import com.example.superheroapi.repository.Repository
import com.example.superheroapi.ui.main.SuperHeroViewModel
import com.example.superheroapi.ui.main.SuperHeroViewModelFactory
import com.example.superheroapi.views.FavirateFragment
import com.example.superheroapi.views.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SuperHeroViewModel
    private val myAdapter by lazy { SuperheroAdatapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        setupRecyclerview()
        searchHero()

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorites -> {
                    val fragment = FavirateFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    val fragment = SettingsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.home -> {
                    val fragment = SettingsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
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

                progressBar_.visibility = View.VISIBLE

                if(query.toString() != null ){
                    getHeroList(query.toString())
                    onLeftButtonClicked()
                }
            }
            setSuggestionsDisabled(true)
        }
    }

    private fun getHeroList(name: String) {

        val repository = Repository()
        val viewModelFactory =SuperHeroViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SuperHeroViewModel::class.java)
        viewModel.getSuperhero(name)
        viewModel.mySuperhero.observe(this, Observer { response ->
            if(response.isSuccessful){
                response.body()?.let { myAdapter.setData(it.results,this) }

                progressBar_.visibility =  View.GONE
            }else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setupRecyclerview() {

        val recyclerView = findViewById<RecyclerView>(R.id. recyclerView)
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

        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorites -> {
                    val fragment = FavirateFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    val fragment = SettingsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.home -> {
                    val fragment = SettingsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }




}
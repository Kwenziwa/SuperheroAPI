package com.example.superheroapi.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapi.model.charecter.SuperHeroModel
import com.example.superheroapi.model.profile.ProfileModel
import com.example.superheroapi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class SuperHeroViewModel(private val repository: Repository): ViewModel() {

    var mySuperhero: MutableLiveData<Response<SuperHeroModel>> = MutableLiveData()
    var myProfile: MutableLiveData<Response<ProfileModel>> = MutableLiveData()

    fun getSuperhero(name: String) {
        viewModelScope.launch {
            val response = repository.getSuperhero(name)
            mySuperhero.value = response
        }
    }

    fun getProfile(id: String) {
        viewModelScope.launch {
            val response = repository.getProfile(id)
            myProfile.value = response
        }
    }

}
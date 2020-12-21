package com.example.superheroapi.repository

import com.example.superheroapi.api.RetrofitInstance
import com.example.superheroapi.model.charecter.SuperHeroModel
import com.example.superheroapi.model.profile.ProfileModel
import retrofit2.Response

class Repository {

    suspend fun getSuperhero(name: String): Response<SuperHeroModel> {
        return RetrofitInstance.api.getSuperhero(name)
    }

    suspend fun getProfile(id: String): Response<ProfileModel> {
        return RetrofitInstance.api.getProfile(id)
    }


}
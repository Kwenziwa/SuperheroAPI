package com.example.superheroapi.api

import com.example.superheroapi.model.charecter.SuperHeroModel
import com.example.superheroapi.model.profile.ProfileModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("search/{name}")
    suspend fun getSuperhero(
        @Path("name") name: String
    ): Response<SuperHeroModel>


    @GET("{id}")
    suspend fun getProfile(
        @Path("id") id: String
    ): Response<ProfileModel>

}
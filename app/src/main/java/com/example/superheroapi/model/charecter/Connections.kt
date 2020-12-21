package com.example.superheroapi.model.charecter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Connections(
    @SerializedName("group-affiliation")
    @Expose
    val group_affiliation: String,
    @SerializedName("relatives")
    @Expose
    val relatives: String
)
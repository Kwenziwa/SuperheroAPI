package com.example.superheroapi.model.charecter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url")
    @Expose
    val url: String
)
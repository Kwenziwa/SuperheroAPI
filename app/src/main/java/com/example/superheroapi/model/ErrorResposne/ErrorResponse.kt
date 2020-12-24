package com.example.superheroapi.model.ErrorResposne

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("error")
    @Expose
    val error: String,
    @SerializedName("response")
    @Expose
    val response: String
)
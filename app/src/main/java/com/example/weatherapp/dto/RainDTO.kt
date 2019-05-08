package com.example.weatherapp.dto

import com.google.gson.annotations.SerializedName

data class RainDTO(

    @SerializedName("3h")
   val threeH:Double
)
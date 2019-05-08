package com.example.weatherapp.response

import com.example.weatherapp.dto.CityDTO
import com.example.weatherapp.dto.ListDTO

data class WeatherByCityNameResponse
    (
    val cod:String,
    val message:String,
    val cnt:Int,
    val list:List<ListDTO>,
    val city: CityDTO
)
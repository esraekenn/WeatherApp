package com.example.weatherapp.dto

data class WeatherDTO(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)
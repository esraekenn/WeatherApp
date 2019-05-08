package com.example.weatherapp.dto

data class ListDTO(
    val dt:Int,
    val main:MainDTO,
    val weather:List<WeatherDTO>,
    val clouds:CloudsDTO,
    val wind:WindDTO,
    val rain:RainDTO,
    val sys:SysDTO,
    val dt_txt:String


)
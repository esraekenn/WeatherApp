package com.example.weatherapp.network

import com.example.weatherapp.response.WeatherByCityNameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface StoriesService {

    @GET("data/{version}/forecast/hourly")
    fun getWeatherById(
        @Path("version")id:String,
        @Query("q")cityName:String,
        @Query("appid")appid:String
    ): Call<WeatherByCityNameResponse>
}
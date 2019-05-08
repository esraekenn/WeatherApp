package com.example.weatherapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.weatherapp.network.RetrofitClient
import com.example.weatherapp.network.StoriesService
import com.example.weatherapp.response.WeatherByCityNameResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.R



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.weatherapp.R.layout.activity_main)

        RetrofitClient.getClient()
            .create(StoriesService::class.java)
            .getWeatherById("2.5", "Istanbul", "4dc4f5e482ec854a688cbf053105e1f4")
            .enqueue(object : Callback<WeatherByCityNameResponse> {
                override fun onFailure(call: Call<WeatherByCityNameResponse>, t: Throwable) {

                    Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
                    Log.v("Esra2", t.message.toString())


                }

                override fun onResponse(
                    call: Call<WeatherByCityNameResponse>,
                    response: Response<WeatherByCityNameResponse>
                ) {

                    val currentWeather = response.body()!!.list[0].main.temp
                    val a = currentWeather.toDouble()
                    val degree = a - 273.15
                //    txtCurrentWeather.setText(degree.toString())
                    when (response.body()!!.list[0].weather[0].main) {
                        "clouds" -> imgWeather.setBackgroundResource(com.example.weatherapp.R.drawable.rain)
                        "rain" -> imgWeather.setBackgroundResource(com.example.weatherapp.R.drawable.sun)
                        "wind" -> imgWeather.setBackgroundResource(com.example.weatherapp.R.drawable.cloud)
                        else -> imgWeather.setBackgroundResource(com.example.weatherapp.R.drawable.rain)
                    }
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()


                }
            })
    }
}

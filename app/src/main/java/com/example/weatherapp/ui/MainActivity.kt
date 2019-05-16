package com.example.weatherapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.network.RetrofitClient
import com.example.weatherapp.network.StoriesService
import com.example.weatherapp.response.WeatherByCityNameResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    private fun Double.toCelsius(): Double {

        return this - 273.15

    }

    private fun Double.formatDouble(): String {
        return if (this >= 10.0) {
            DecimalFormat("##").format(this)

        } else {

            DecimalFormat("#").format(this)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

                    val currentWeather = response.body()!!.list[0].main.temp.toCelsius().formatDouble()

                    txtCurrentWeather.text = currentWeather
                    when (response.body()!!.list[0].weather[0].main) {
                        // "clouds" -> imgWeather.setBackgroundResource(R.drawable.cloudy)
                        "rain" -> imgWeather.setBackgroundResource(R.drawable.rain)
                        // "wind" -> imgWeather.setBackgroundResource(R.drawable.wind)
                        else -> imgWeather.setBackgroundResource(R.drawable.rainbow)
                    }
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()


                    when (response.body()!!.list[0].weather[0].main) {
                        "clouds" -> lytBack.setBackgroundResource(R.drawable.weather_back)
                        "rain" -> lytBack.setBackgroundResource(R.drawable.weather_back_2)
                        "wind" -> lytBack.setBackgroundResource(R.drawable.weather_back_3)
                        else -> lytBack.setBackgroundResource(R.drawable.weather_back)
                    }


                  //  imgWeather1.setBackgroundResource(R.drawable.min_rain)
                    txtWeather1.text = response.body()!!.list[1].main.temp.toCelsius().formatDouble()
                    txtWeather2.text = response.body()!!.list[2].main.temp.toCelsius().formatDouble()
                    txtWeather3.text = response.body()!!.list[3].main.temp.toCelsius().formatDouble()

                    txtDate1.text = response.body()!!.list[1].dt_txt
                    txtDate2.text = response.body()!!.list[2].dt_txt
                    txtDate3.text = response.body()!!.list[3].dt_txt
                    txtCurrentWeather2.text=response.body()!!.list[0].weather[0].description


                }
            })
    }

}

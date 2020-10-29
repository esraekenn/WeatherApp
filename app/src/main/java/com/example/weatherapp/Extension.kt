package com.example.weatherapp

import java.text.DecimalFormat

fun Double.toCelsius(): Double {

    return this - 273.15

}

 fun Double.formatDouble(): String {
    return if (this >= 10.0) {
        DecimalFormat("##").format(this)

    } else {

        DecimalFormat("#").format(this)
    }

}
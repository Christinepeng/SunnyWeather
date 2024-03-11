package com.example.sunnyweather.data.services

import android.database.Observable
import com.example.sunnyweather.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("v4/weather/forecast")
    fun getWeatherByLocation(@Query("location") location: String, @Query("apikey") apikey: String): Response<WeatherData>
}
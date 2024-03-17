package com.example.sunnyweather.data.repositories

import android.util.Log
import com.example.sunnyweather.data.services.WeatherService
import com.example.sunnyweather.model.WeatherData
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Instant

class WeatherRepository {
    private val gson = GsonBuilder()
        .registerTypeAdapter(Instant::class.java, JsonDeserializer { json, _, _ ->
            Instant.parse(json.asJsonPrimitive.asString)
        })
        .create()

    private val weatherService: WeatherService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.tomorrow.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WeatherService::class.java)
    }

    suspend fun getWeatherByLocation(location: String): WeatherData? {
        return try {
            val response = weatherService.getWeatherByLocation(location)
            if (response.isSuccessful) {
                Log.e("xd", "Receive response ${response.body()}")
                response.body()
            } else {
                null // or handle errors as needed
            }
        } catch (e: Exception) {
            Log.e("xd", "Exception $e")
            null // or handle exceptions as needed
        }
    }
}
package com.example.weatherapponcompose.data

import com.example.weatherapponcompose.data.mappers.toWeatherInfo
import com.example.weatherapponcompose.data.remote.WeatherAPI
import com.example.weatherapponcompose.domain.repository.WeatherRepository
import com.example.weatherapponcompose.domain.util.Resource
import com.example.weatherapponcompose.domain.weather.WeatherInfo
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPI
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}
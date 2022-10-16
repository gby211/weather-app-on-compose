package com.example.weatherapponcompose.data

import com.example.weatherapponcompose.data.local.Weather
import com.example.weatherapponcompose.data.local.WeatherDataBase
import com.example.weatherapponcompose.data.mappers.toWeatherDataMap
import com.example.weatherapponcompose.data.mappers.toWeatherInfo
import com.example.weatherapponcompose.data.remote.WeatherAPI
import com.example.weatherapponcompose.domain.repository.WeatherRepository
import com.example.weatherapponcompose.domain.util.Resource
import com.example.weatherapponcompose.domain.weather.WeatherInfo
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPI,
    private val dataBase: WeatherDataBase
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        val res = try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }

        if (res is Resource.Success) {
            //сохранить
            try {
                dataBase.weatherLocationDao().insertWeather(res.data!!.weatherDataPerDay as Weather)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return res
        } else {
            //получить из бд
            try {
                return Resource.Success(dataBase.weatherLocationDao().getWeather().toWeatherDataMap())
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return Resource.Error("No data in database")
        }
    }
}
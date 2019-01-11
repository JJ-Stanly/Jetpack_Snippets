package com.jjlab.roompersistence.data

class WeatherRepository private constructor(private val weatherDAO: WeatherDAO) {

    fun getWeatherReport() = weatherDAO.getAll()

    companion object {

        @Volatile private var instance: WeatherRepository? = null

        fun getInstance(weatherDAO: WeatherDAO) = instance ?: synchronized(this) {
                instance ?: WeatherRepository(weatherDAO).also { instance = it }
            }


    }

}
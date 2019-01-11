package com.jjlab.roompersistence.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDAO {
    @Query("SELECT * FROM weatherData")
    fun getAll(): LiveData<List<WeatherEntity>> // LivaData wrapper is used here.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherEntity: WeatherEntity)

    @Query("DELETE FROM weatherData")
    fun deleteAll()
}